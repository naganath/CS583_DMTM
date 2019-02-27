import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String DATA_FILE_NAME = "data-1.txt";
    private static final String PARA_FILE_NAME = "para1-1.txt";
    private static final String SDC = "SDC";
    public static void main(String[] args) throws  Exception {
        List<Sequence> dataSequence = readData(DATA_FILE_NAME);
        Map<Integer, Double> misMap = readParameter(PARA_FILE_NAME);
        Double sdc = misMap.remove(-1);
        MS_GSP ms_gsp = new MS_GSP(misMap, sdc, dataSequence);
        Map<Integer, List<Sequence>> kLengthSeqList =  ms_gsp.execute();
        for (Map.Entry<Integer, List<Sequence>> entry : kLengthSeqList.entrySet()) {
            System.out.println(" Number of Length " + entry.getKey() + " Frequency Sequences: " + entry.getValue().size());
            for (Sequence se : entry.getValue()) {
                System.out.println(se.toString());
            }
        }
    }

    private static Map<Integer, Double> readParameter(String fileName)  throws Exception {
        final String SPLIT_CONSTANT = " = ";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String inputStr = null;
        Map<Integer, Double> parameterMap = new HashMap<>();
        while ((inputStr = br.readLine()) != null) {
            if(inputStr.contains(SDC)) {
                parameterMap.put(-1, getDoubleValue(inputStr.split(SPLIT_CONSTANT)[1]));
                break;
            }
            parameterMap.put(getItemValue(inputStr.split(SPLIT_CONSTANT)[0]),
                    getDoubleValue(inputStr.split(SPLIT_CONSTANT)[1]));
        }
        return parameterMap;
    }

    private static Double getDoubleValue(String misStr) {
        return Double.parseDouble(misStr);
    }

    private static Integer getItemValue(String itemStr) {
        return Integer.parseInt(itemStr.replace("MIS(", "").replace(")", ""));
    }

    private static List<Sequence> readData(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String inputStr = null;
        List<Sequence> sequenceList = new ArrayList<>();
        while((inputStr = br.readLine()) != null) {
            Sequence s = new Sequence();
            List<List<Integer>> elementList = new ArrayList<>();
            String elementStr[]= inputStr.substring(2, inputStr.length() -2).split("}\\{");
            for(String element : elementStr) {
                List<Integer> itemSet = new ArrayList<>();
                    String itemStrArray[] = element.split(", ");
                    for (String itemStr : itemStrArray) {
                        itemSet.add(Integer.parseInt(itemStr));
                    }
                elementList.add(itemSet);
            }
            s.setElements(elementList);
            sequenceList.add(s);
        }
        return sequenceList;
    }
}



