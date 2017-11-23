public class SaveSharedPreferences{

	String fileName = "Example";
	SharedPreferences sharedPreferences;
    	SharedPreferences.Editor editor;

	public SaveSharedPreferences(String fileName){

		this.fileName = fileName;

		sharedPreferences = getSharedPreferences(fileName, 0);
    		editor = sharedPreferences.edit();
	}

	public WriteTextToCommit(String key, String text){
		editor.putString(key, text);
    		editor.commit();
	}
}
