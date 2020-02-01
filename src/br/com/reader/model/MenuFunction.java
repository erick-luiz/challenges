package br.com.reader.model;

import br.com.reader.utils.ReaderUtil;

public enum MenuFunction {

	REPORT_GENERATOR(1) {
		@Override
		public void process() {
			ReaderUtil.proccessReport();
		}
	},
	EXIT(0) {
		@Override
		public void process() {
			System.exit(0);
		}
	},
	DEFAULT(-1) {
		@Override
		public void process() {
			System.exit(-1);
		}
	};
	
	private int optionCode;
	
	MenuFunction(int optCode){
		this.optionCode = optCode;
	}
	
	public abstract void process();
	
	public static MenuFunction get(int code) {
		
		for(MenuFunction menuItem: MenuFunction.values()) {
			if(menuItem.optionCode == code) {
				return menuItem;
			}
		}
		
		return DEFAULT;
	}
	
	
}
