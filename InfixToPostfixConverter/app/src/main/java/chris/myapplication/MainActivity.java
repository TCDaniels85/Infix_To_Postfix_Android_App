package chris.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView resultText;
    public Button convertBtn;
    public Button clearBtn;
    public EditText infixExpression;
    public TextView resultHeaderText;
    InfixConversion conversion;
    String infixExp;
    public String errorMessage;
    public Switch modeSwitch;
    public String newText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Show app icon in action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_launcher_c_foreground);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_c_foreground);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_c_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);
        //assign view member variables
        resultText = findViewById(R.id.resultText);
        convertBtn = findViewById(R.id.convertBtn);
        clearBtn = findViewById(R.id.clearBtn);
        modeSwitch = findViewById(R.id.mode_switch);
        infixExpression = findViewById(R.id.userInputBox);
        resultHeaderText = findViewById(R.id.resultHeaderText);
        conversion = new InfixConversion();

        //onclick listener for convert button
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConvertBtnPress(infixExpression.getText().toString());
            }
        });
        //on click listener for clear button
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInputText();
            }

        });

        //Find's out app's nightmode state and sets the text and switch to correct positions
        int currentThemeMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if(currentThemeMode == Configuration.UI_MODE_NIGHT_NO){
            modeSwitch.setText("Switch to Dark Mode");
            modeSwitch.setChecked(true); //sets initial check if darkmode enabled on device
            //resultText.setText(newText);
            //System.out.println("Hi this is text:" + newText);

        } else if (currentThemeMode == Configuration.UI_MODE_NIGHT_YES){
            modeSwitch.setText("Switch to Light Mode");
        }

        //mode switch listener to change theme once switch is checked by user
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    newText = resultText.getText().toString();

                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                //System.out.println("Hi this is text:" + newText);
            }

        });

    }


    /**
     * Method to set the result text once the convert button is pressed. Contains logic to
     * decide whether to display a result or an error message
     * @param userExpression expression entered by user
     */
    public void onConvertBtnPress (String userExpression){

        if (expressionValid(userExpression) == true) {	//Checks the user has entered an expression
            resultHeaderText.setText("Infix expression: " + userExpression);
            resultText.setText("Postfix expression: " + startConversion(userExpression));
            clearInputText();
        } else {
            resultHeaderText.setText("");
            resultText.setText(errorMessage);
        }


    }

    /**
     * Clears any text in the user has entered
     */
    public void clearInputText(){
        infixExpression.getText().clear();
    }

    /**
     * Creates an instance of the InfixConversion class to perform a conversion
     * on the expression entered by the user
     * @param userExpression infix expression from user input
     * @return error or postfix expression string to be displayed to user
     */
    public String startConversion(String userExpression){
        return conversion.convert(userExpression);
    }

    public boolean expressionValid(String userExpression){
        //System.out.println(infixExp);
        infixExp = userExpression;
        //conversion = new InfixConversion();

        if (infixExp.equals("")) {	//Checks the user has entered an expression
            errorMessage = "Please enter an expression!";
            return false;
        } else if (!conversion.checkParentheses(infixExp)) {
            errorMessage = "Please ensure expression does not have any missing parentheses!";
            return false;
        } else if(infixExp.length() > 20) {
            errorMessage = "Your infix expression is more than 20 characters long!";
            return false;
        } else if(conversion.containsNumbers(infixExp)) {
            errorMessage = "Your expression contains numbers, please only use letters!";
            return false;
        } else {
            return true;
        }
    }
}