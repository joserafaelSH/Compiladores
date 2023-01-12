package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {
	private final static int BUFFER_SIZE = 5;
	int[] buffer;
	int pointer;
	
	private void createBuffer() {
		this.buffer = new int[BUFFER_SIZE * 2];
		this.pointer = 0;
		loadBuffer1();
	}
	
	//buffer da esquerda
	private void loadBuffer1() {
		for(int i = 0; i< BUFFER_SIZE; i++) {
			try {
				this.buffer[i] = is.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(this.buffer[i] ==-1) {
				break;
			}
		}
	}
	
	//buffer da direita
	private void loadBuffer2() {
		for(int i = BUFFER_SIZE; i<BUFFER_SIZE * 2 ; i++) {
			try {
				this.buffer[i] = is.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(this.buffer[i] ==-1) {
				break;
			}
		}
	}
	
	private void addPointer() {
		this.pointer+=1;
		if(this.pointer == BUFFER_SIZE) {
			this.loadBuffer2();
		}else if(this.pointer == BUFFER_SIZE * 2){
			this.loadBuffer1();
			this.pointer = 0;
		}
	}
	
	public void subPointer() {
		this.pointer-=1;
		if(this.pointer < 0) {
			this.pointer = BUFFER_SIZE * 2 - 1;
		}
	}
	
	private InputStream is;
	
	public FileReader(String path) {
		try {
			is = new FileInputStream(new File(path));
			this.createBuffer();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	private int readCharFromBuffer() {
		int ret = this.buffer[this.pointer];
		this.addPointer();
		return ret;
	}
	
	public int readNextChar() {
		int c = this.readCharFromBuffer();
		System.out.print((char)c);
		return c;
	}
}
