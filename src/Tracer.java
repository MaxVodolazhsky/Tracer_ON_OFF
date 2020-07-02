import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tracer {

    public static void main(String[] args) {
        try {
            ArrayList<String> list = new ArrayList<>();
            File file = new File("C:\\Program Files (x86)\\SIGUR access management\\server\\sphinxd\\Tracer.cfg");

            Service(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Program Files (x86)\\SIGUR access management\\server\\sphinxd\\Tracer.cfg"), "UTF-8"));

            String tracerOn = "target=file";
            String tracerOff = "target=null";
            String filter = "filenameFilterActive=0";

            while (reader.ready()) {
                list.add(reader.readLine());
            }

            System.out.println("Включить Tracer - нажмите 1");
            System.out.println("Выключить Tracer - нажмите 2");

            Scanner scanner = new Scanner(System.in);

            switch (scanner.next()) {
                case ("1") : for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).equals("target=null")){
                        list.remove(i);
                        list.add(i,tracerOn);
                    }
                    if(list.get(i).equals("filenameFilterActive=1")){
                        list.remove(i);
                        list.add(i,filter);
                    }
                } break;

                case ("2") : for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).equals("target=file")){
                        list.remove(i);
                        list.add(i,tracerOff);
                    }
                    if(list.get(i).equals("filenameFilterActive=1")){
                        list.remove(i);
                        list.add(i,filter);
                    }
                } break;
            }

            reader.close();
            file.delete();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Program Files (x86)\\SIGUR access management\\server\\sphinxd\\Tracer.cfg"), "UTF-8"));
            for (String str : list) {
                writer.write(str + "\n");
            }
            writer.close();

            Service(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Service (boolean var) {

        String[] commandStart = {"cmd.exe",  "/c", "sc", "start", "\"Sphinx service module\""};
        String[] commandStop = {"cmd.exe",  "/c", "sc", "stop", "\"Sphinx service module\""};

        try {
            if (var) {
                Process process = new ProcessBuilder(commandStart).start();

            } else {
                Process process = new ProcessBuilder(commandStop).start();

            }

        } catch(Exception ex) {
            System.out.println("Exception : "+ex);
        }
    }
}
