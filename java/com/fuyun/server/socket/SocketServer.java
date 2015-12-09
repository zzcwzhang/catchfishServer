/**
 * 
 */
package com.fuyun.server.socket;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioProcessor;
import org.apache.mina.transport.socket.nio.NioSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.fuyun.server.debug.DebugWll;



/**
 * 
 * @author lushouzhi
 *
 */
public class SocketServer {
	
	private static final int SERVER_PORT = ServerConfig.getSocketPort();
	private static final boolean IS_TCP_NODELAY = ServerConfig.isTcpNodelay();
	private static final int WORKER_POOL_MAX = ServerConfig.getWorkerPoolMax();
	private static final int SOCKET_BOTH_IDLE = ServerConfig
			.getSocketBothIdle();
	private static final int WORKER_POOL_MIN = ServerConfig.getWorkerPoolMin();
	private static final int WORKER_POOL_IDLE = ServerConfig
			.getWorkerPoolIdle();
	private static final int READ_BUFFER_SIZE = ServerConfig
			.getReadBufferSize();
	private static final int WRITE_BUFFER_SIZE = ServerConfig
			.getWriteBufferSize();
	private static final int RECEIVE_BUFFER_SIZE = ServerConfig
			.getReadBufferSize();
	private static final int SERVER_MAX_BACKLOG = ServerConfig
			.getServerMaxBacklog();
	private static final int SOCKET_WRITE_TIMEOUT = ServerConfig
			.getSocketWriteTimeout();

	private static final SimpleIoProcessorPool<NioSession> pool = new SimpleIoProcessorPool<NioSession>(
			NioProcessor.class, ServerConfig.getServerNioProcess());
	private SocketAcceptor acceptor;
	private InetSocketAddress address;
	private IoFilter cmdAttackFilter;
	private IoFilter byteAttackFilter;
	private ProtocolCodecFactory protocolCodecFactory;
	private IoHandler ioHandler;
	private static final String THREAD_NAME = "Socket线程池";
	private static final ThreadGroup THREAD_GROUP = new ThreadGroup(THREAD_NAME);
	private static final NamedThreadFactory THREAD_FACTORY = new NamedThreadFactory(
			THREAD_GROUP, "Socket线程池");
	private static final OrderedThreadPoolExecutor FILTER_EXECUTOR = new OrderedThreadPoolExecutor(
			WORKER_POOL_MIN, WORKER_POOL_MAX, WORKER_POOL_IDLE,
			TimeUnit.SECONDS, THREAD_FACTORY);
	
	public SocketServer(ProtocolCodecFactory protocolCodecFactory,
			IoHandler ioHandler) {
		this.protocolCodecFactory=protocolCodecFactory;
		this.ioHandler=ioHandler;
	}

	public void start(KeepAliveFilter filter) throws Exception {
		IoBuffer.setUseDirectBuffer(false);
		IoBuffer.setAllocator(new SimpleBufferAllocator());
		this.acceptor = new NioSocketAcceptor(pool);
		this.acceptor.setReuseAddress(true);
		this.acceptor.setBacklog(SERVER_MAX_BACKLOG);
		this.acceptor.getSessionConfig().setAll(getSessionConfig());
		MdcInjectionFilter mdcInjectionFilter = new MdcInjectionFilter(); //将IoSession的主键属性注入线程映射表MDC中
		DefaultIoFilterChainBuilder filterChain = this.acceptor
				.getFilterChain();
		filterChain.addLast("mdcInjectionFilter", mdcInjectionFilter);
		if (this.byteAttackFilter != null) {
			filterChain.addLast("byteAttackFilter", this.byteAttackFilter);
		}

		if (this.cmdAttackFilter != null) {
			filterChain.addLast("cmdAttackFilter", this.cmdAttackFilter);
		}
		filterChain.addLast("codecFactory", new ProtocolCodecFilter(
				protocolCodecFactory));

		//心跳设置
		if(DebugWll.Heart){
			filterChain.addLast("heartbeat", filter);
		}
		

		filterChain.addLast("threadPool", new ExecutorFilter(FILTER_EXECUTOR));

		this.acceptor.setHandler(ioHandler);
		this.address = new InetSocketAddress(SERVER_PORT);
		this.acceptor.bind(this.address);
	}

	public SocketSessionConfig getSessionConfig() {
		SocketSessionConfig sessionConfig = new DefaultSocketSessionConfig();
		sessionConfig.setSoLinger(0);
		sessionConfig.setKeepAlive(true);
		sessionConfig.setReuseAddress(true);
		sessionConfig.setTcpNoDelay(IS_TCP_NODELAY);
		sessionConfig.setBothIdleTime(SOCKET_BOTH_IDLE);
		sessionConfig.setReadBufferSize(READ_BUFFER_SIZE);
		sessionConfig.setSendBufferSize(WRITE_BUFFER_SIZE);
		sessionConfig.setWriteTimeout(SOCKET_WRITE_TIMEOUT);
		sessionConfig.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
		return sessionConfig;
	}

	public void stop() {
		if (this.acceptor != null) {
			this.acceptor.unbind();
			this.acceptor.dispose();
			this.acceptor = null;
		}

		if (FILTER_EXECUTOR != null) {
			FILTER_EXECUTOR.shutdown();
			try {
				FILTER_EXECUTOR.awaitTermination(5000L, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
//				GameLog.error("停服抛出了异常", e);
			}
		}
	}

	public IoFilter getFloodByteAttackFilter() {
		return this.byteAttackFilter;
	}

	public void setFloodByteAttackFilter(IoFilter floodByteAttackFilter) {
		this.byteAttackFilter = floodByteAttackFilter;
	}

	public IoFilter getFloodCmdAttackFilter() {
		return this.cmdAttackFilter;
	}

	public void setFloodCmdAttackFilter(IoFilter floodCmdAttackFilter) {
		this.cmdAttackFilter = floodCmdAttackFilter;
	}
}
