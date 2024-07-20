package genclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReturnedObject {

    String a;
    Integer b;
    Float c;

    public ReturnedObject(String a, Integer b, Float c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public ReturnedObject(String a, Integer b){
        this.a = a;
        this.b = b;
    }
    public ReturnedObject(Float c){
        this.c = c;
    }
    public ReturnedObject(){
    }
}
