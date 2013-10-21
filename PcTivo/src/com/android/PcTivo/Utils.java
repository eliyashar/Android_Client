package com.android.PcTivo;

public class Utils {
	public enum PcTivoHttpResponse{
		OK_200(200),
		NO_UPDATE(206),
		NOT_FOUND(404),
		Forbiden(403);
		int value;
		private PcTivoHttpResponse(int value) {
			this.value = value;
		}
	}
		
}
