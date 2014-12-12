package com.example.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.thepiproject.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NumbersFragment extends Fragment implements OnClickListener{

	private static final int MAX_NUMBER_OF_QUESTIONS = 5;
	
	Button answerButton;
	int questionPosition;
	TextView question;
	EditText answerText;
	Numbers current;
	int numberOfAskedQuestions;
	List<Numbers> numbers;

	private void getRandomQuestionPosition(){
		Random rd = new Random();
		questionPosition = rd.nextInt(numbers.size());
		Log.e("Random",Integer.toString(questionPosition));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		answerButton = (Button) getView().findViewById(R.id.answerNumericalSeries);
		answerButton.setOnClickListener(this);
		numbers = new ArrayList<Numbers>(30);
		openFile();
		answerText = (EditText) getView().findViewById(R.id.answer);
		numberOfAskedQuestions=0;
		question = (TextView) getView().findViewById(R.id.numericProblem);
		nextQuestion();
	}
	
	
	public void nextQuestion(){
		if(numberOfAskedQuestions == MAX_NUMBER_OF_QUESTIONS){
			Toast.makeText(getActivity(), "GameOver", Toast.LENGTH_LONG).show();
		}
		getRandomQuestionPosition();
		for(int i = 0;i<numbers.size();i++){
			Log.e("Numbers:",numbers.get(i).toString());
		}
		current = numbers.get(questionPosition);
		Log.e("Question",current.toString());
		question.setText(current.getQuestion());
		numberOfAskedQuestions++;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.logic_fragment,container,false);
	}

	private void openFile() {

		final InputStream is = this.getResources().openRawResource(
				R.raw.numbers);
		final BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int counter = 0;
		String line;
		try {
			while ((line = bf.readLine()) != null) {
				Numbers num = new Numbers();
				num.setQuestion(line);
				Log.e("Reader",line);
				line = bf.readLine();
				num.setAnswer(line);
				Log.e("Reader",line);
				numbers.add(counter, num);
				System.out.printf("Added: %s ",numbers.get(counter).toString());
				System.out.println();
				counter++;
				System.out.println(num.getQuestion() +" " + num.getAnswer());
			}
		} catch (IOException e) {
			Log.e("Reader","Exception");
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onClick(View v) {
		
		OnAnswerSelectedListener answerSelected = (OnAnswerSelectedListener) getActivity();
		
		if(v.getId() == R.id.answerNumericalSeries){
			String answer = answerText.getText().toString();
			if(answer.equals(current.getAnswer())){
				answerSelected.correctAnswer();
				numbers.remove(questionPosition);
				nextQuestion();
				answerText.setText("");
			} else {
				answerSelected.wrongAnswer();
				numbers.remove(questionPosition);
				nextQuestion();
				answerText.setText("");
				
			}
		}
	}
	
	public static interface OnAnswerSelectedListener{
		void correctAnswer();
		void wrongAnswer();
	}
}