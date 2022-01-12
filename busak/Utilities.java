package busak;

public class Utilities {
	
	private Utilities(){}

	public static int eskatuInt(int max){
		int num = 0;
		boolean ok = false;
		while(!ok){
			try{
				num = Integer.parseInt(System.console().readLine());
				if(num>=0 && num<=max){
					ok = true;
				}else{
					System.out.println("Sartu 0 eta "+max+" tartean");
				}
			}catch(NumberFormatException e){
				System.out.println("Sartu zenbaki bat");
			}
		}
		return num;
	}
}
