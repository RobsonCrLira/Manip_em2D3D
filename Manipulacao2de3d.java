
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Exercicio extends JFrame{
	
	static int gerador[][] = new int [8][8]; //matrix para geração do objeto
	static float mundo[][] = new float [8][8];
	static float m[][] = new float [3][8]; // Recebe os pontos 
	static float mTranslacao[][] = new float[3][8]; // matrix de translação
	static float mEscala[][] = new float[3][8]; // matrix de escala
	static float mRotacao[][] = new float[3][8]; // matrix de Rotação
	
	
	static float transx, transy, transz; // variaveis para a translação
	static float esx, esy, esz; // variaveis para a escala
	static double ang; // variaveis para rotação
	
	static int matrix = 8,eixo = 3; // 
	
	
	
	public Exercicio(JPanel panel) {
		getContentPane().add(panel);
		setSize(900,900);
		setVisible(true);
		setAlwaysOnTop(true);
	}
	
	public static void main(String[] args) {
		
		//Recebe a matrix
		/*for(int i = 0; i<matrix; i++) {
			m[0][i] = Float.parseFloat(JOptionPane.showInputDialog("Digite a Coordenada de x"+i));//X
			m[1][i] = Float.parseFloat(JOptionPane.showInputDialog("Digite a Coordenada de y"+i));//Y
			m[2][i] = Float.parseFloat(JOptionPane.showInputDialog("Digite a Coordenada de z"+i));//Z
		}*/
		
		m[0][0] = 100; m[1][0] = 100; m[2][0] = 100;
		m[0][1] = 200; m[1][1] = 100; m[2][1] = 100;
		m[0][2] = 200; m[1][2] = 100; m[2][2] = 150;
		m[0][3] = 100; m[1][3] = 100; m[2][3] = 150;
		
		m[0][4] = 100; m[1][4] = 150; m[2][4] = 100;
		m[0][5] = 200; m[1][5] = 150; m[2][5] = 100;
		m[0][6] = 200; m[1][6] = 150; m[2][6] = 150;
		m[0][7] = 100; m[1][7] = 150; m[2][7] = 150;
	
//	    ang = Float.parseFloat(JOptionPane.showInputDialog("Digite o angulo de rotação"));
//		transx = Float.parseFloat(JOptionPane.showInputDialog("Digite a Translação em X"));
//		transy = Float.parseFloat(JOptionPane.showInputDialog("Digite a Translação em Y"));
//		transz = Float.parseFloat(JOptionPane.showInputDialog("Digite a Translação em Z"));
//	
		esx = Float.parseFloat(JOptionPane.showInputDialog("Digite a escala em X"));
		esy = Float.parseFloat(JOptionPane.showInputDialog("Digite a escala em Y"));
		esz = Float.parseFloat(JOptionPane.showInputDialog("Digite a escala em Z"));
		
		escala();
	}

	public static void translacao() { //tudo certo]
		
		for(int i = 0; i<matrix;i++) {
			mTranslacao[0][i] = m[0][i] + transx;
			mTranslacao[1][i] = m[1][i] + transy;
			mTranslacao[2][i] = m[2][i] + transz;
		}
	}

	public static void escala() { //tudo certo

		for(int i = 0; i<matrix;i++) {
			mEscala[0][i] = m[0][i] * esx;
			mEscala[1][i] = m[1][i] * esy;
			mEscala[2][i] = m[2][i] * esz;
		}
		
		calculaMundo();
		criar();
	}

	public static void rotacao() {
	//alteração
		
		ang = ang *(Math.PI / 180);
		for (int i= 0; i<matrix;i++) {
			mRotacao[0][i] = (float) ((m[0][i] * Math.cos(ang)) - (m[1][i] * Math.sin(ang)));
			mRotacao[1][i] = (float) ((m[0][i] * Math.sin(ang)) + (m[1][i] * Math.cos(ang)));
		}
		
	}

	public static void calculaMundo() {
		double al = 0;
		double alp = (float) al * (Math.PI/ 180);
		float mAux[][]= new float[3][8];
		double z[] = new double[2];
		
		
		int tx = 639 , ty = 479; 
		float minX = -2000, minY = -2000, maxX= 2000, maxY = 2000;
		for(int i = 0; i < matrix; i++) {
			mAux[0][i] = ((mEscala[0][i]-minX) /(maxX-minX)) * tx;
			mAux[1][i] = (1-(mEscala[1][i]-minY)/(maxY-minY)) * ty;
			mAux[2][i] = mEscala[2][i]; 
		}
		
		
		z[0] = Math.cos(alp);
		z[1] = Math.sin(alp);
		
		for (int i = 0; i<matrix; i++) {
			
			mundo[0][i] = (float) (mAux[0][i]+mAux[2][i]*z[0]);
			mundo[1][i] = (float) (mAux[1][i]-mAux[2][i]*z[1]);
			System.out.println(mundo[0][i]+" "+mundo[1][i]);
		}
		
	}
	
	public static void criar() {
		
		for(int i = 0; i<matrix; i++) {
			for(int j = 0; j<2; j++) {
				gerador[j][i] = Math.round(mundo[j][i]);
			}
		}
		Figura();
	}	
	
	public static void Figura() {
		new Exercicio(new Triangulo(new Point(gerador[0][0],gerador[1][0]),
									new Point(gerador[0][1],gerador[1][1]),
									new Point(gerador[0][2],gerador[1][2]),
									new Point(gerador[0][3],gerador[1][3]),
									new Point(gerador[0][4],gerador[1][4]),
									new Point(gerador[0][5],gerador[1][5]),
									new Point(gerador[0][6],gerador[1][6]),
									new Point(gerador[0][7],gerador[1][7])
				));
		
		}
	private static class Triangulo extends JPanel {
		Point p1,p2,p3, p4,p5,p6,p7,p8;
		
		public Triangulo(Point ponto1, Point ponto2, Point ponto3, Point ponto4, Point ponto5, Point ponto6, Point ponto7, Point ponto8) {
			this.p1 = ponto1;
			this.p2 = ponto2;
			this.p3 = ponto3;
			this.p4 = ponto4;
			this.p5 = ponto5;
			this.p6 = ponto6;
			this.p7 = ponto7;
			this.p8 = ponto8;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.red);
			//primeiro quadrado
//			g.drawLine(p1.x, p1.y, p2.x, p2.y);
//			g.drawLine(p2.x, p2.y, p3.x, p3.y);
//			g.drawLine(p3.x, p3.y, p4.x, p4.y);
//			g.drawLine(p4.x, p4.y, p1.x, p1.y);
//			//segundo quadrado
			g.drawLine(p5.x, p5.y, p6.x, p6.y);
			g.drawLine(p6.x, p6.y, p7.x, p7.y);
			g.drawLine(p7.x, p7.y, p8.x, p8.y);
			g.drawLine(p8.x, p8.y, p5.x, p5.y);
			//ligação
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			g.drawLine(p3.x, p3.y, p3.x, p4.y);
			g.drawLine(p5.x, p5.y, p6.x, p6.y);
			g.drawLine(p7.x, p7.y, p8.x, p8.y);
		}
	}
}



