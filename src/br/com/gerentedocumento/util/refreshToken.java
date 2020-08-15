package br.com.gerentedocumento.util;

import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;


public class refreshToken {
	/*static void refreshAccessToken() throws IOException {
		try {
			TokenResponse response = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
					"1//0hS4SN3CdDLRACgYIARAAGBESNwF-L9Ir3BoB6Pxdx_VKdyhTtKSLh2oJwpMl_jVcm15A9sg0wdCZZ6Dnp1ig1PYTKzc8PY7PC_Y",
					"520982118656-9qo078f70lvbs852akvoie4ufb449rfk.apps.googleusercontent.com",
					"T5OGea9aOm-iKLzmWini73WW").execute();
			System.out.println("Access token: " + response.getAccessToken());
		} catch (TokenResponseException e) {
			if (e.getDetails() != null) {
				System.err.println("Error: " + e.getDetails().getError());
				if (e.getDetails().getErrorDescription() != null) {
					System.err.println(e.getDetails().getErrorDescription());
				}
				if (e.getDetails().getErrorUri() != null) {
					System.err.println(e.getDetails().getErrorUri());
				}
			} else {
				System.err.println(e.getMessage());
			}
		}
	}*/
	/**
	 * Possuindo estes parâmetros o método retornará o token de acesso com validade de 1 hora.
	 * @param refreshToken
	 * @param clientId
	 * @param clientSecret
	 * @return token de acesso com validade de 1 hora.
	 * @throws IOException
	 */
	public String getNewToken(String refreshToken, String clientId, String clientSecret) throws IOException {
		ArrayList<String> scopes = new ArrayList<>();

		scopes.add(CalendarScopes.CALENDAR);

		/*TokenResponse tokenResponse = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
				refreshToken, clientId, clientSecret).setScopes(scopes).setGrantType("refresh_token").execute();*/
		TokenResponse tokenResponse = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
				refreshToken, clientId, clientSecret).execute();

		return tokenResponse.getAccessToken();
	}

	public static void main(String[] args) {

		
		try {
			refreshToken teste = new refreshToken();
			String token = "1//0h-mhrt2gq0IxCgYIARAAGBESNwF-L9IryREvwmOS5r8kbf-7yQemPTJF7Oo-ivRVgEG_ab7_M-EADo0wUszp-v_6JlwKK4a3eiY";
			String client = "520982118656-a5m5n3enr7u73of2aa9g0gchpriduvnt.apps.googleusercontent.com";
			String secret = "zm-kZzpP6igDt05bLlPRqVRx";
			System.out.println(teste.getNewToken(token, client, secret));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
