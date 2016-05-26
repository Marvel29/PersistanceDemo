package com.gmail.marvelfds.persistancedemo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by Core i7 on 5/26/2016.
 */
@Table(name="input_value")
public class InputValue extends Model {
 @Column(name="value")
    String text;

    public InputValue() {
        super();
    }

    public InputValue(String text) {
        super();
        this.text = text;
    }

    public static InputValue theMostRecentValue(){

        return new Select().from(InputValue.class).orderBy("id desc").limit("").executeSingle();
    }
}
