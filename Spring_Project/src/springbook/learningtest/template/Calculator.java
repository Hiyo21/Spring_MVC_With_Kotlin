package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public <T> T lineReadTemplate(String filePath, T initValue, LineCallback<T> lineCallback) throws IOException{
		BufferedReader br = null;
		T res = initValue;
		String line = null;
		
		try{
			br = new BufferedReader(new FileReader(filePath));
			while((line = br.readLine()) != null){
				res = lineCallback.doSomethingWithLine(line, res);
			}
			return res;
		}catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			throw ioe;
		}finally{
			try{
				if(br != null) br.close();
			}catch(IOException ioe2){
				System.out.println(ioe2.getMessage());
			}
		}		
	}
	
	public Integer calculate(String filePath, char operator) throws IOException {
		
		return lineReadTemplate(filePath, 0, (x, y) -> {
			switch(operator){
				case '+':
					y += Integer.valueOf(x);
					break;
				case '-':
					y -= Integer.valueOf(x);
					break;
				case '*':
					y *= Integer.valueOf(x);
					break;
				case '/':
					y /= Integer.valueOf(x);
					break;
				default:
					throw new UnsupportedOperationException();
				}
			return y;
		});
	}
	
	public String concatenate(String filePath) throws IOException {
		return lineReadTemplate(filePath, "", (x, y) -> { return y + x; });
	}
	
	
}

