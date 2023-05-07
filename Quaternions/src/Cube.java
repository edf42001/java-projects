import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

// Class: Cube
// Written by: Ethan Frank
// Date: Apr 26, 2018
// Description:
public class Cube implements Comparable<Cube>{
	private static final int[][] connections = new int[][] {{0,4},{4,6},{6,2},{2,0},{1,5},{5,7},{7,3},{3,1},{0,1},{4,5},{6,7},{2,3}};
//	private static final int[][] connections = new int[][] {{0,2,3,1}, {4,5,7,6}, {1,5,7,3}, {0,4,5,1}, {6,2,3,7}, {0,4,6,2}};
	private int[][] points;
	private double[][] transformedPoints;
	private boolean behind;
	private Color color;
	private int dist;
	
	public Cube(int x, int y, int z){
		points = new int[8][3];
		transformedPoints = new double[8][3];
		
		for(int i = 0; i<2; i++){
			for(int j = 0; j<2; j++){
				for(int k = 0; k<2; k++){
					points[k+2*j+4*i] = new int[] {(i+x)*64,(j+y)*64,(k+z)*64};
				}
			}
		}
		color = Math.random()<0.5?Color.BLACK:Color.BLUE;
	}
	
	public void transform(double[] camera, double[] cameraAngles, int FOV){
		for(int i = 0; i<points.length; i++){
			for(int j = 0; j<3; j++){
				transformedPoints[i][j] = points[i][j];
			}
		}
		behind = false;
		a: for(int p = 0; p<transformedPoints.length; p++){
			double[] point = new double[3];
			for(int i = 0; i<3; i++){
				point[i] = transformedPoints[p][i];
			}
	                //rotate the point and add it's projection onto the plane to newShape
			for(int i = 0; i<3; i++){
				point[i] = point[i]-camera[i];
			}
	                
	        double camRads = Math.toRadians(cameraAngles[0]);
            Matrix rotMatrix = new Matrix(new double[][] {{Math.cos(camRads),-Math.sin(camRads),0},
										                	{Math.sin(camRads),Math.cos(camRads),0},
										                	{0,0,1}});        
           	camRads = Math.toRadians(cameraAngles[1]);
            rotMatrix = new Matrix(new double[][] {{Math.cos(camRads),0,Math.sin(camRads)},
                                     {0,1,0},
                                     {-Math.sin(camRads),0,Math.cos(camRads)}}).dot(rotMatrix);
            point = rotMatrix.dot(Matrix.singleColumnMatrixFromArray(point)).toArray();
           	double dist = point[0];
            if (dist >0){
            	for(int i = 0; i<3; i++){
            		point[i] = (int) (point[i]/dist * 400 / Math.tan(Math.toRadians(FOV/2.0)));
            	}
            }else{
        		behind = true;
                break a;
            }
            for(int i = 0; i<3; i++){
            	transformedPoints[p][i] = point[i];
			}
            
            this.dist = (int) (Math.pow(camera[0]-transformedPoints[0][0], 2) + Math.pow(camera[1]-transformedPoints[0][1], 2) + Math.pow(camera[2]-transformedPoints[0][2], 2));
		}
	}
	
	public void draw(Graphics2D g2){
		if(!behind){// && transformedPoints[0][1]>-600 && transformedPoints[0][1]<600 && transformedPoints[0][2]<900 && transformedPoints[0][2]>-600){
			g2.setStroke(new BasicStroke(2));
//			g2.setColor(color);
			for(int[] con : connections){
				//draw the edges connecting the points
				g2.drawLine((int) (-transformedPoints[con[0]][1]+400), (int) (-transformedPoints[con[0]][2]+400),
						(int) (-transformedPoints[con[1]][1]+400), (int) (-transformedPoints[con[1]][2]+400));
//				g2.fillPolygon(new int[] {(int) (-transformedPoints[con[0]][1]+400),(int) (-transformedPoints[con[1]][1]+400),
//											(int) (-transformedPoints[con[2]][1]+400),(int) (-transformedPoints[con[3]][1]+400)}, 
//								new int[] {(int) (-transformedPoints[con[0]][2]+400),(int) (-transformedPoints[con[1]][2]+400),
//											(int) (-transformedPoints[con[2]][2]+400),(int) (-transformedPoints[con[3]][2]+400)}, 4);
			}
		}
	}
	
	public int getDist(){
		return this.dist;
	}
	@Override
	public int compareTo(Cube o) {
		return dist-o.getDist();
	}
}
 