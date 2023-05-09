package map;
import java.util.Arrays;

public class Noise {
	
	public static double[] getNoise(int size,double step,int oct) {
		double[] noise= new double[size];
		for(int i=0; i<noise.length;i++) {
			noise[i]=Math.random();
		}
		noise=toNoise(noise,step, oct);
		return noise;
	}
	
	public static double[] toNoise(double[] input, double step, int oct) {
		double[] noise=new double[input.length];
		for(int idx=0;idx<input.length;idx++) {
			double power=1, sum=0,d=-1;
			for(double i=input.length, layer=1;(int)i!=0; sum+=power, power=power*step,d=-1, layer++) {
				if(layer==1) {
					d=input[0];
				}else {
					for(double s=0;d<0;s+=i) {
						if(idx==s) {
							d=input[(int)s];
						}else if((s+i)>idx) {
							if((s+i)<input.length) {
								d=(input[(int)s]-((input[(int)s]-input[(int)(s+i)])/i)*(idx-(int)s));
							}else {
								d=(input[(int)s]-((input[(int)s]-input[0])/i)*(idx-(int)s));
							}
						}
					}
				}
				noise[idx]+=(power*d);
				if((int)i==1) { 
					i=0;
				}
				if(layer==1) {
					i=(i*0.5);
					if((int)i==0) {
						i=1;
					}
				}else {
					i=i/2;
				}
				if (layer==oct) {
					i=0;
				}
			}
			noise[idx]=noise[idx]/sum;
			if(idx==0) {
			}
		}
		return noise;
	}
	
	public static double[][] toNoise2D(double[][] input, double step, int oct) {
		double[][] noise=new double[input.length][input[0].length];
		int total=0;
		int mount=0;
		int peak=0;
		while(total<input.length*10 ||mount<input.length*2 ||peak<input.length*1||(mount>input.length*3 ||peak>input.length*2)) {
			for(int i=0; i<noise.length;i++) {
				input[i]=Noise.getStatic(input[i].length);
			}
			total=0;
			mount=0;
			peak=0;
			noise=new double[input.length][input[0].length];
			for(int idy=0;idy<input.length;idy++) {
				for(int idx=0;idx<input[idy].length;idx++) {
					int x1=-1,x2=-1,y1=-1,y2=-1;
					double power=1, sum=0, d=-1, n1=-1, n2=-1;
					for(double i=input[idy].length, layer=1;(int)i!=0; sum+=power, power=power*step,d=-1, layer++, x1=-1, x2=-1, y1=-1, y2=-1,n1=-1,n2=-1) {
						if(layer==1) {
							d=input[0][0];
						}else {
							for(double s=0;x1<0;s+=i) {
								if((s+i)>=idx) {
									if(((s+i)<input[idy].length)) {
										x1=(int)s;
										x2=(int)(s+i);
									}else {
										x1=(int)s;
										x2=input[idy].length-1;
									}
								}
							}
							for(double s=0;y1<0;s+=i) {
								if((s+i)>=idy) {
									if(((s+i)<input.length)) {
										y1=(int)s;
										y2=(int)(s+i);
									}else {
										y1=(int)s;
										y2=input.length-1;
									}
								}
							}
							n1=(input[y1][x1]-((input[y1][x1]-input[y1][x2])/i)*(idx-x1));
							n2=(input[y2][x1]-((input[y2][x1]-input[y2][x2])/i)*(idx-x1));
							d=(n1-((n1-n2)/i)*(idy-y1));			
						}
						noise[idy][idx]+=(power*d);
						if((int)i==1) { 
							i=0;
						}
						if(layer==1) {
							i=(i*0.5);
							if((int)i==0) {
								i=1;
							}
						}else {
							i=i/2;
						}
						if (oct==layer) {
							i=0;
						}
					}
					noise[idy][idx]=noise[idy][idx]/sum;
					noise[idy][idx]=noise[idy][idx]*(1-Math.abs(((Math.abs(idx-noise.length/2.0))+Math.abs(noise.length/2.0-idy))/2.0)/((noise.length)/2.0));
					if(noise[idy][idx]>1) {
						noise[idy][idx]=1;
					}
					if(((int)(255*noise[idy][idx]))>130) {
						total++;
					}
					if(((int)(255*noise[idy][idx]))>170) {
						mount++;
					}
					if(((int)(255*noise[idy][idx]))>180) {
						peak++;
					}
				}
			}
		}
		System.out.println(total);
		return noise;
	}
	
	public static double[] getStatic(int size) {
		double[] noise= new double[size];
		for(int i=0; i<noise.length;i++) {
			noise[i]=Math.random();
		}
		return noise;
	}
}