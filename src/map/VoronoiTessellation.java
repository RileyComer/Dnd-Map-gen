package map;

public class VoronoiTessellation {
	
	public static String[][] Biom(int w, int h, int d){
		String[][] vT= new String[h][w];
		for(int y=0; y<vT.length;y++) {
			for(int x=0; x<vT[y].length;x++) {
				vT[y][x]="";
			}
		}
		for(int i=0; i<d; i++) {
			int x, y;
			x=(int)(Math.random()*w);
			y=(int)(Math.random()*h);
			while(vT[y][x].equals(null)) {
				x=(int)(Math.random()*w+1);
				y=(int)(Math.random()*h+1);
			}
			vT[y][x]=i+"-0";
		}
		boolean done=false;
		for(int layer=0;!done;layer++) {
			done=true;
			for(int x=0; x<w;x++) {
				for(int y=0; y<h;y++) {
					int nx=x-1, ny=y-1;
					if((!(vT[y][x].equals(""))&&vT[y][x].substring((vT[y][x].indexOf("-")+1)).equals(""+layer))) {	
						for(int i=0; i<4;i++) {
							if(i==0) {
								if(x==vT[y].length-1) {
									nx=vT[y].length-1;
								}else {
									nx=x+1;
								}
								ny=y;
							}else if(i==1) {
								if(y==vT.length-1) {
									ny=vT.length-1;
								}else {
									ny=y+1;
								}
								nx=x;
							}else if(i==2) {
								if(y==0) {
									ny=0;
								}else {
									ny=y-1;
								}
								nx=x;
							}else if(i==3) {
								if(x==0) {
									nx=0;
								}else {
									nx=x-1;
								}
								ny=y;
							}
							if((vT[ny][nx].equals(""))) {
								vT[ny][nx]=(vT[y][x].substring(0,(vT[y][x].indexOf("-")))+"-"+(layer+1));
								done=false;
							}
						}
						vT[y][x]=(vT[y][x].substring(0,(vT[y][x].indexOf("-")))+"-!");
					}
				}
			}
		}
		return vT;
	}
}
