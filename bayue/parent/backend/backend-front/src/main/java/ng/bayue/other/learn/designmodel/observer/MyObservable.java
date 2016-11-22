package ng.bayue.other.learn.designmodel.observer;

import java.io.File;
import java.util.Observable;

public class MyObservable extends Observable{
	
	private boolean flag;
	
	private File file;
	
	public void advisor(boolean flag){
		this.flag = flag;
		sendAdvisor();
	}
	
	public void monitorFile(File file){
		this.file = file;
		sendAdvisor();
	}
	
	private void sendAdvisor(){
		setChanged();
		notifyObservers();
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

}
