package BookMap.PentaRim.Book;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BookState {

    NOT("안읽은"),
    DONE("읽은"),
    READING("읽는중인"),
    WISH("읽고싶은");

    @JsonValue
    private final String value;

    private BookState(String value){
            this.value = value;
    }

    public String getValue(){
            return value;
    }

    @JsonCreator
    public static BookState from(String value){
        for(BookState state: BookState.values()){
            if(state.getValue().equals(value)){
                return state;
            }
        }
        return null;
    }
}

