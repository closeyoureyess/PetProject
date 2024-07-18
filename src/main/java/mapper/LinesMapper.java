package mapper;

import java.util.LinkedList;
import java.util.List;

public class LinesMapper {

    public List<StringBuffer> stringToStringBuffer(List<String> listString) {
        List<StringBuffer> newStringBuffersList = new LinkedList<>();
        for (int i = 0; i < listString.size(); i++) {
            newStringBuffersList.add(new StringBuffer(listString.get(i)));
        }
        return newStringBuffersList;
    }

    public List<String> stringBufferToString(List<StringBuffer> listStringBuffer){
        List<String> newStringList = new LinkedList<>();
        for (int i = 0; i < listStringBuffer.size(); i++){
            newStringList.add(String.valueOf(listStringBuffer.get(i)));
        }
        return newStringList;
    }

    public Integer stringBufferToInteger(StringBuffer stringBuffer){
        return Integer.valueOf(stringBuffer.toString());
    }
}
