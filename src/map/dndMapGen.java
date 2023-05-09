package map;
import javax.swing.JFrame;

public class dndMapGen {

	public static void main(String[] args) {
		int w=100,h=100, size=10, dots=130; 	//100, 100, 10, 130//int w=80,h=80, size=10, dots=130;
		double noise[][]=new double[h][w];
		double step=0.75;//0.9 or 0.7//1
		int oct=0;//6
		String[][] vT=VoronoiTessellation.Biom(w, h, dots);
		noise=Noise.toNoise2D(noise, step, oct);
		JFrame map= new JFrame ("Title");
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setSize((w*size)+16, (h*size)+39);
		DrawMap l= new DrawMap(noise,"T", size, vT, dots);
		map.add(l);
		map.setVisible(true);
	}
}
