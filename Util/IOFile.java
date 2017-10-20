public class IOFile
{
    private final String fileName = "Example.txt";

    public IOFile(String fileName)
    {
    	this.fileName = fileName;
    }

    public void createFile()
    {
    	try{
        	private OutputStreamWriter file = new OutputStreamWriter(openFileOutput(file_name, Context.MODE_PRIVATE));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
    	}
    }

    public void writeFile(String text) {

        try{
            file.write(text);
            file.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String readFile() {
        String result = "";
        String currentLineFile;

        try {
            InputStream file = openFileInput(file_name);

            if (file != null) {
                InputStreamReader reader = new InputStreamReader(file);

                BufferedReader buff = new BufferedReader(reader);

                while ((currentLineFile = buff.readLine()) != null) {
                    result += currentLineFile;
                }
                file.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
