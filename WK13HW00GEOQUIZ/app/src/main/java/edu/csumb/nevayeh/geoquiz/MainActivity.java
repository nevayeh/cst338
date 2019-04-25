package edu.csumb.nevayeh.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    Button mNextButton;

    int mCurrentIndex = 0;

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ButtonListener listener = new ButtonListener();

        /*
        private OnClickListener buttonListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.trueButton:
                        System.out.println("true was clicked");
                        Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.falseButton:
                        System.out.println("false was clicked");
                        Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nextButton:
                        System.out.println("next was clicked");
                        Toast.makeText(MainActivity.this, "Hard coded strings are bad mmkay", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
        */

        //mTrueButton.setOnClickListener(buttonListener);
        //mFalseButton.setOnClickListener(buttonListener);
        //mNextButton.setOnClickListener(buttonListener);

        mQuestionTextView = (TextView) findViewById(R.id.questionTextField);

        mTrueButton = (Button) findViewById(R.id.trueButton);
        mTrueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.falseButton);
        mFalseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }

    public void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
