import java.util.Arrays;

class Matrix {

	//local variables
	private int rows;
	private int cols;
	private double[][] matrix;

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//constructor
	Matrix(int r, int c) {
		rows = r;
		cols = c;
		matrix = new double[rows][cols];
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//constructor from 2D array
	Matrix(double[][] m) {
		matrix = m;
		rows = m.length;
		cols = m[0].length;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//print matrix
	public void print() {
		System.out.println(this);
	}

	//Convert to string
	public String toString() {
		String ret = "";
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				ret+=matrix[i][j] + "  ";
			}
			ret+="\n";
		}
		return ret;
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  

	//multiply by scalar
	public void multiply(double n ) {
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] *= n;
			}
		}
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//return a matrix which is this matrix dot product parameter matrix 
	public Matrix dot(Matrix n) {
		Matrix result = new Matrix(rows, n.cols);

		if (cols == n.rows) {
			//for each spot in the new matrix
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<n.cols; j++) {
					double sum = 0;
					for (int k = 0; k<cols; k++) {
						sum+= matrix[i][k]*n.matrix[k][j];
					}
					result.matrix[i][j] = sum;
				}
			}
		}

		return result;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//set the matrix to random doubles between -1 and 1
//	public void randomize() {
//		for (int i =0; i<rows; i++) {
//			for (int j = 0; j<cols; j++) {
//				matrix[i][j] = (MyRand.randFloat()*2-1);
//			}
//		}
//	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//add a scalar to the matrix
	public void add(double n) {
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] += n;
			}
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	///return a matrix which is this matrix + parameter matrix
	public Matrix add(Matrix n) {
		Matrix newMatrix = new Matrix(rows, cols);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] + n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//return a matrix which is this matrix - parameter matrix
	public Matrix subtract(Matrix n) {
		Matrix newMatrix = new Matrix(cols, rows);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] - n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//return a matrix which is this matrix * parameter matrix (element wise multiplication)
	public Matrix multiply(Matrix n) {
		Matrix newMatrix = new Matrix(rows, cols);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] * n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//return a matrix which is the transpose of this matrix
	public Matrix transpose() {
		Matrix n = new Matrix(cols, rows);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[j][i] = matrix[i][j];
			}
		}
		return n;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//Creates a single column array from the parameter array
	public static Matrix singleColumnMatrixFromArray(double[] point) {
		Matrix n = new Matrix(point.length, 1);
		for (int i = 0; i< point.length; i++) {
			n.matrix[i][0] = point[i];
		}
		return n;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//sets this matrix from an array
	public void fromArray(double[] arr) {
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< cols; j++) {
				matrix[i][j] =  arr[j+i*cols];
			}
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------    
	//returns an array which represents this matrix
	public double[] toArray() {
		double[] arr = new double[rows*cols];
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< cols; j++) {
				arr[j+i*cols] = (int) matrix[i][j];
			}
		}
		return arr;
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//for ix1 matrixes adds one to the bottom
	public Matrix addBias() {
		Matrix n = new Matrix(rows+1, 1);
		for (int i =0; i<rows; i++) {
			n.matrix[i][0] = matrix[i][0];
		}
		n.matrix[rows][0] = 1;
		return n;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//applies the activation function(sigmoid) to each element of the matrix
	public Matrix activate() {
		Matrix n = new Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[i][j] = sigmoid(matrix[i][j]);
			}
		}
		return n;
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------    
	//sigmoid activation function
	double sigmoid(double x) {
		double y = 1 / (1 + Math.pow((double)Math.E, -x));
		return y;
	}
	//returns the matrix that is the derived sigmoid function of the current matrix
	public Matrix sigmoidDerived() {
		Matrix n = new Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[i][j] = (matrix[i][j] * (1- matrix[i][j]));
			}
		}
		return n;
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//returns the matrix which is this matrix with the bottom layer removed
	public Matrix removeBottomLayer() {
		Matrix n = new Matrix(rows-1, cols);      
		for (int i =0; i<n.rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[i][j] = matrix[i][j];
			}
		}
		return n;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//Mutation function for genetic algorithm 

	public void mutate(double mutationRate) {
//		//for each element in the matrix
//		for (int i =0; i<rows; i++) {
//			for (int j = 0; j<cols; j++) {
//				double rand = MyRand.randFloat();
//				if (rand<mutationRate) {//if chosen to be mutated
//					matrix[i][j] += (MyRand.randFloat()-0.5)/2.5;//add a random value to it(can be negative)
//
////					//set the boundaries to 1 and -1
////					if (matrix[i][j]>1) {
////						matrix[i][j] = 1;
////					}
////					if (matrix[i][j] <-1) {
////						matrix[i][j] = -1;
////					}
//				}
//			}
//		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//returns a matrix which has a random number of values from this matrix and the rest from the parameter matrix
//	public Matrix crossover(Matrix partner) {
//		Matrix child = new Matrix(rows, cols);
//
//		//pick a random point in the matrix
//		int randC = (int) Math.floor(MyRand.randInt(cols));
//		int randR = (int) Math.floor(MyRand.randInt(rows));
//		for (int i =0; i<rows; i++) {
//			for (int j = 0; j<cols; j++) {
//
//				if ((i< randR)|| (i==randR && j<=randC)) { //if before the random point then copy from this matric
//					child.matrix[i][j] = matrix[i][j];
//				} else { //if after the random point then copy from the parameter array
//					child.matrix[i][j] = partner.matrix[i][j];
//				}
//			}
//		}
//		return child;
//	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//return a copy of this matrix
	public Matrix clone() {
		Matrix clone = new  Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				clone.matrix[i][j] = matrix[i][j];
			}
		}
		return clone;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	//Get rows
	public int getRows(){
		return rows;
	}
	
	//Get columns
	public int getCols(){
		return cols;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------  
	public static void insertInto(double[] big, double[] small, int index){
		for(int i = index; i<index+small.length && i<big.length; i++){
			big[i] = small[i-index];
		}
	}
	
	public static double[] subset(double[] arr, int index, int length){
		double[] ret = new double[length];
		for(int i = 0; i<length; i++){
			ret[i] = arr[i+index];
		}
		return ret;
	}
}