package map;

import java.awt.*;
import javax.swing.*;


public class DrawMap extends JPanel{
	double[][] noise;
	String isc="T";
	int size=0;
	String[][] vT;
	int[] bList;
	int[][] biomTypes= {
						{0,255,0},
						{0,200,0},
						{0,150,0},
						{255,255,0}
						};
	
	public DrawMap(double[][]input,String color,int pixSize, String[][] biom,int d) {
		noise=input;
		isc=color;
		size=pixSize;
		vT=biom;
		bList= new int[d];
		for(int i=0; i<d;i++) {
			bList[i]=(int)(Math.random()*biomTypes.length);
		}
	}
	
	public void paintComponent(Graphics g) {
		int color=0;
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		for(int r=0; r<noise.length;r++) {
			for(int c=0;c<noise[r].length;c++) {
				if (isc.contentEquals("T")) {
					color=((int)(255*noise[r][c]));
					if (color<=130) {
						g.setColor(new Color(0,0,(color)));
					}else if(color<=135) {
						g.setColor(new Color(255-color,255-color,0));
					}else if(color<=170) {
						int i=Integer.parseInt(vT[r][c].substring(0,1));
						g.setColor(new Color((int)(biomTypes[bList[i]][0]*noise[r][c]),(int)(biomTypes[bList[i]][1]*noise[r][c]),(int)(biomTypes[bList[i]][2]*noise[r][c])));
					}else if(color<180) {
						g.setColor(new Color((int)(150*noise[r][c]),(int)(150*noise[r][c]),(int)(150*noise[r][c])));
					}else {
						g.setColor(new Color(color,color,color));
					}
				}else if(isc.contentEquals("F")){
					color=((int)(255*noise[r][c]));
					if (color>=170) {
						g.setColor(new Color(255,215,color));
					}else if(color>=150) {
						g.setColor(new Color(color,color,0));
					}else {
						g.setColor(new Color(color,0,0));
					}
				}else {
					g.setColor(new Color(color,color,color));
				}
				g.fillRect(c*size, r*size, size, size);
			}
		}
	}
}