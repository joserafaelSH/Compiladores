package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {
	private final static int BUFFER_SIZE = 20;
	int[] buffer;
	int pointer;
	int currentBuffer;
	int startLex;
	String lex;
	
	
	public String getLex() {
		return lex;
	}

	public void setLex(String lex) {
		this.lex = lex;
	}

	private void createBuffer() {
		this.currentBuffer = 2;
		this.startLex = 0;
		this.lex = "";
		this.buffer = new int[BUFFER_SIZE * 2];
		this.pointer = 0;
		loadBuffer1();
	}
	
	//buffer da esquerda
	private void loadBuffer1() {
		if(this.currentBuffer == 2) {
			this.currentBuffer = 1;
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
	}
	
	//buffer da direita
	private void loadBuffer2() {
		if(this.currentBuffer == 1) {
			this.currentBuffer = 2;
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
		this.lex = this.lex.substring(0, this.lex.length() -1 );
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
		//System.out.println(this);
		this.addPointer();
		return ret;
	}
	
	public int readNextChar() {
		int c = this.readCharFromBuffer();
		this.lex+=(char)c;
		return c;
	}
	
	public void zero() {
		this.pointer = this.startLex;
		this.lex = "";
	}
	
	public void accept() {
		this.startLex = this.pointer;
		this.lex = "";
	}

	
	@Override
	public String toString() {
		String ret = "Buffer: [";
		for(int i : this.buffer) {
			char c = (char)i;
			if(Character.isWhitespace(c)) {
				ret += ' ';
			}else {
				ret += (char)i;
			}
		}
		
		ret += "]\n";
		ret += "		";
		for(int i = 0; i< BUFFER_SIZE * 2; i++) {
			if(i == this.startLex && i==this.pointer) {
				ret+="%";
			}else if(i == this.startLex) {
				ret+="^";
			}else if(i == this.pointer) {
				ret+="*";
			}else {
				ret+=" ";
			}
		}
		return ret;
	}
	
	
	
	
}
