package test.java.com.example;

import static org.junit.Assert.*;


import org.junit.Test;

import com.example.exception.NoSquareException;
import com.example.model.Matrix;
import com.example.service.MatrixMathematics;

public class MatrixMathematicsTests {


	@Test
	public void testDeterminant() throws NoSquareException{
		double data1[][] = {{-1,2,5},{1,2,3},{-2,8,10}};
		Matrix matrice = new Matrix(data1);
		assert(MatrixMathematics.determinant(matrice)==32);
	}
	@Test (expected = NoSquareException.class)
	public void testDeterminant_Exception() throws NoSquareException{
		double data1[][] = {{-1,2,5},{1,2,3}};
		Matrix matrice = new Matrix(data1);
		MatrixMathematics.determinant(matrice);
	}
	@Test
	public void testDeterminant_size() throws NoSquareException{
		double data1[][] = {{5}};
		Matrix matrice = new Matrix(data1);
		assertTrue(MatrixMathematics.determinant(matrice)==5);
	}
	

	@Test
	public void testCofactor() throws NoSquareException{
		double data1[][] = {{-1,2,5},{1,2,3},{-2,8,10}};
		double data2[][] = {{-4,-16,12},{20,0,4},{-4,8,-4}};
		Matrix matrice = new Matrix(data1);
		Matrix matrice_cofactor = new Matrix(data2);
		assertArrayEquals(MatrixMathematics.cofactor(matrice).getValues(),matrice_cofactor.getValues());
	}

}
