package com.fuyun.server.db.util;

import java.util.Random;

public class ChineseName {
	 
	
	private static ChineseName instance;
	
	public static ChineseName getInstance(){
		if(instance == null){
			instance = new ChineseName();
		}
		return instance;
	}
	
	
    String[] sName = new String[95];
    String[] Name = new String[79];
   
  
     
    public ChineseName() {
        sName[0]="白";
        sName[1]="白";
        sName[2]="蔡";
        sName[3]="曹";
        sName[4]="陈";
        sName[5]="戴";
        sName[6]="窦";
        sName[7]="邓";
        sName[8]="狄";
        sName[9]="杜";
        sName[10]="段";
        sName[11]="范";
        sName[12]="樊";
        sName[13]="房";
        sName[14]="风";
        sName[15]="符";
        sName[16]="福";
        sName[17]="高";
        sName[18]="古";
        sName[19]="关";
        sName[20]="郭";
        sName[21]="毛";
        sName[22]="韩";
        sName[23]="胡";
        sName[24]="花";
        sName[25]="洪";
        sName[26]="侯";
        sName[27]="黄";
        sName[28]="贾";
        sName[29]="蒋";
        sName[30]="金";
        sName[31]="廖";
        sName[32]="梁";
        sName[33]="李";
        sName[34]="林";
        sName[35]="刘";
        sName[36]="龙";
        sName[37]="陆";
        sName[38]="卢";
        sName[39]="罗";
        sName[40]="马";
        sName[41]="牛";
        sName[42]="庞";
        sName[43]="裴";
        sName[44]="彭";
        sName[45]="戚";
        sName[46]="齐";
        sName[47]="钱";
        sName[48]="乔";
        sName[49]="秦";
        sName[50]="邱";
        sName[51]="裘";
        sName[52]="仇";
        sName[53]="沙";
        sName[54]="商";
        sName[55]="尚";
        sName[56]="邵";
        sName[57]="沈";
        sName[58]="师";
        sName[59]="施";
        sName[60]="宋";
        sName[61]="孙";
        sName[62]="童";
        sName[63]="万";
        sName[64]="王";
        sName[65]="魏";
        sName[66]="卫";
        sName[67]="吴";
        sName[68]="武";
        sName[69]="萧";
        sName[70]="肖";
        sName[71]="项";
        sName[72]="许";
        sName[73]="徐";
        sName[74]="薛";
        sName[75]="杨";
        sName[76]="羊";
        sName[77]="阳";
        sName[78]="易";
        sName[79]="尹";
        sName[80]="俞";
        sName[81]="赵";
        sName[82]="钟";
        sName[83]="周";
        sName[84]="郑";
        sName[85]="朱";
        sName[86]="东方";
        sName[87]="独孤";
        sName[88]="慕容";
        sName[89]="欧阳";
        sName[90]="司马";
        sName[91]="西门";
        sName[92]="尉迟";
        sName[93]="长孙";
        sName[94]="诸葛";
         
        Name[0]="皑艾哀";
        Name[1]="安黯谙";
        Name[2]="奥傲敖骜翱";
        Name[3]="昂盎";
        Name[4]="罢霸";
        Name[5]="白佰";
        Name[6]="斑般";
        Name[7]="邦";
        Name[8]="北倍贝备";
        Name[9]="表标彪飚飙";
        Name[10]="边卞弁忭";
        Name[11]="步不";
        Name[12]="曹草操漕";
        Name[13]="苍仓";
        Name[14]="常长昌敞玚";
        Name[15]="迟持池赤尺驰炽";
        Name[16]="此次词茨辞慈";
        Name[17]="独都";
        Name[18]="东侗";
        Name[19]="都";
        Name[20]="发乏珐";
        Name[21]="范凡反泛帆蕃";
        Name[22]="方访邡昉";
        Name[23]="风凤封丰奉枫峰锋";
        Name[24]="夫符弗芙";
        Name[25]="高皋郜镐";
        Name[26]="洪红宏鸿虹泓弘";
        Name[27]="虎忽湖护乎祜浒怙";
        Name[28]="化花华骅桦";
        Name[29]="号浩皓蒿浩昊灏淏";
        Name[30]="积极济技击疾及基集记纪季继吉计冀祭际籍绩忌寂霁稷玑芨蓟戢佶奇诘笈畿犄";
        Name[31]="渐剑见建间柬坚俭";
        Name[32]="刊戡";
        Name[33]="可克科刻珂恪溘牁";
        Name[34]="朗浪廊琅阆莨";
        Name[35]="历离里理利立力丽礼黎栗荔沥栎璃";
        Name[36]="临霖林琳";
        Name[37]="马"; 
        Name[38]="贸冒貌冒懋矛卯瑁";
        Name[39]="淼渺邈";
        Name[40]="楠南";
        Name[41]="片翩";
        Name[42]="潜谦倩茜乾虔千";
        Name[43]="强羌锖玱";
        Name[44]="亲琴钦沁芩矜";
        Name[45]="清庆卿晴";
        Name[46]="冉然染燃";
        Name[47]="仁刃壬仞";
        Name[48]="沙煞";
        Name[49]="上裳商";
        Name[50]="深审神申慎参莘";
        Name[51]="师史石时十世士诗始示适炻";
        Name[52]="水";
        Name[53]="思斯丝司祀嗣巳";
        Name[54]="松颂诵";
        Name[55]="堂唐棠瑭";
        Name[56]="统通同童彤仝";
        Name[57]="天田忝";
        Name[58]="万宛晚";
        Name[59]="卫微伟维威韦纬炜惟玮为";
        Name[60]="吴物务武午五巫邬兀毋戊";
        Name[61]="西席锡洗夕兮熹惜";
        Name[62]="潇萧笑晓肖霄骁校";
        Name[63]="熊雄";
        Name[64]="羊洋阳漾央秧炀飏鸯";
        Name[65]="易意依亦伊夷倚毅义宜仪艺译翼逸忆怡熠沂颐奕弈懿翊轶屹猗翌";
        Name[66]="隐因引银音寅吟胤訚烟荫";
        Name[67]="映英影颖瑛应莹郢鹰";
        Name[68]="幽悠右忧猷酉";
        Name[69]="渔郁寓于余玉雨语预羽舆育宇禹域誉瑜屿御渝毓虞禺豫裕钰煜聿";
        Name[70]="制至值知质致智志直治执止置芝旨峙芷挚郅炙雉帜";
        Name[71]="中忠钟衷";
        Name[72]="周州舟胄繇昼";
        Name[73]="竹主驻足朱祝诸珠著竺";
        Name[74]="卓灼灼拙琢濯斫擢焯酌";
        Name[75]="子资兹紫姿孜梓秭";
        Name[76]="宗枞";
        Name[77]="足族祖卒";
        Name[78]="作左佐笮凿";
    }
 
  
 
    public String getNames(int len) {
      
    	String name = "";
    	Random random = new Random();
    	
		int index_xing = random.nextInt(sName.length);
		String xing = sName[index_xing];
		
		int index_ming = random.nextInt(Name.length);
    	int len_ming = Name[index_ming].length()-2;
    	int c = 0;
    	if(len_ming>=1){
    		 c = random.nextInt(len_ming);
    	}
    	String ming = Name[index_ming].substring(c, c+1);
    	
    	if(len >= 3){
    		int index_ming2 = random.nextInt(Name.length);
    		int len_ming2 = Name[index_ming2].length()-2;
    		int c2 = 0;
        	if(len_ming2>=1){
        		 c2 = random.nextInt(len_ming2);
        	}
        	String ming2 = Name[index_ming2].substring(c2, c2+1);
        	ming = ming + ming2; 
        	
        	
        	
    	}
    	
    	if(len >= 4){
    		int index_ming3 = random.nextInt(Name.length);
    		int len_ming3 = Name[index_ming3].length()-2;
    		int c3 = 0;
        	if(len_ming3>=1){
        		 c3 = random.nextInt(len_ming3);
        	}
        	String ming3 = Name[index_ming3].substring(c3, c3+1);
        	ming = ming + ming3; 
    	}
    	
    	
    	name = xing+ming;
    	return name;
    }
 
   
}
