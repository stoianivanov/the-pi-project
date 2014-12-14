package com.example.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.thepiproject.R;

public class NumbersFragment extends Fragment implements OnClickListener {

	private static final int MAX_NUMBER_OF_QUESTIONS = 5;

	ImageButton answerButton;
	TextView question;
	EditText answerText;

	Button buttonClear;
	Button buttonAdd1;
	Button buttonAdd2;
	Button buttonAdd3;
	Button buttonAdd4;
	Button buttonAdd5;
	Button buttonAdd6;
	Button buttonAdd7;
	Button buttonAdd8;
	Button buttonAdd9;
	Button buttonAdd0;
	Button buttonAddPeriod;
	Button buttonAddMinus;

	String enteredAnswer;
	
	OnAnswerSelectedListener answerSelected;

	int questionPosition;
	Numbers current;
	int numberOfAskedQuestions;
	List<Numbers> numbers;

	private void getRandomQuestionPosition() {
		Random rd = new Random();
		questionPosition = rd.nextInt(numbers.size());
	}

	@Override
	public void onStart() {
		super.onStart();

		answerSelected = (OnAnswerSelectedListener) getActivity();

		answerButton = (ImageButton) getView().findViewById(
				R.id.answerNumericalSeries);
		answerButton.setOnClickListener(this);

		buttonClear = (Button) getView().findViewById(R.id.buttonClear);
		buttonAdd1 = (Button) getView().findViewById(R.id.buttonAdd1);
		buttonAdd2 = (Button) getView().findViewById(R.id.buttonAdd2);
		buttonAdd3 = (Button) getView().findViewById(R.id.buttonAdd3);
		buttonAdd4 = (Button) getView().findViewById(R.id.buttonAdd4);
		buttonAdd5 = (Button) getView().findViewById(R.id.buttonAdd5);
		buttonAdd6 = (Button) getView().findViewById(R.id.buttonAdd6);
		buttonAdd7 = (Button) getView().findViewById(R.id.buttonAdd7);
		buttonAdd8 = (Button) getView().findViewById(R.id.buttonAdd8);
		buttonAdd9 = (Button) getView().findViewById(R.id.buttonAdd9);
		buttonAdd0 = (Button) getView().findViewById(R.id.buttonAdd0);
		buttonAddPeriod = (Button) getView().findViewById(R.id.buttonAddPeriod);
		buttonAddMinus = (Button) getView().findViewById(R.id.buttonAddMinus);

		buttonClear.setOnClickListener(this);
		buttonAdd1.setOnClickListener(this);
		buttonAdd2.setOnClickListener(this);
		buttonAdd3.setOnClickListener(this);
		buttonAdd4.setOnClickListener(this);
		buttonAdd5.setOnClickListener(this);
		buttonAdd6.setOnClickListener(this);
		buttonAdd7.setOnClickListener(this);
		buttonAdd8.setOnClickListener(this);
		buttonAdd9.setOnClickListener(this);
		buttonAdd0.setOnClickListener(this);
		buttonAddPeriod.setOnClickListener(this);
		buttonAddMinus.setOnClickListener(this);
		
		numbers = new ArrayList<Numbers>(30);
		openFile();

		answerText = (EditText) getView().findViewById(R.id.answer);
		answerText.setFocusable(false);

		numberOfAskedQuestions = 0;

		question = (TextView) getView().findViewById(R.id.numericProblem);
		nextQuestion();
	}

	public void nextQuestion() {
		if (numberOfAskedQuestions == MAX_NUMBER_OF_QUESTIONS) {
			answerSelected.nextFragment();
		}
		enteredAnswer = "";
		getRandomQuestionPosition();
		current = numbers.get(questionPosition);
		Log.e("Question", current.toString());
		question.setText(current.getQuestion());
		numberOfAskedQuestions++;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.logic_fragment, container, false);
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
				Log.e("Reader", line);
				line = bf.readLine();
				num.setAnswer(line);
				Log.e("Reader", line);
				numbers.add(counter, num);
				counter++;
			}
		} catch (IOException e) {
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

		if (v.getId() == R.id.answerNumericalSeries) {
			String answer = answerText.getText().toString();
			if (answer.equals(current.getAnswer())) {
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
		} else if (v.getId() == R.id.buttonAdd0){
			enteredAnswer += "0";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd1){
			enteredAnswer += "1";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd2){
			enteredAnswer += "2";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd3){
			enteredAnswer += "3";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd4){
			enteredAnswer += "4";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd5){
			enteredAnswer += "5";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd6){
			enteredAnswer += "6";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd7){
			enteredAnswer += "7";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd8){
			enteredAnswer += "8";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAdd9){
			enteredAnswer += "9";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAddPeriod){
			enteredAnswer += ".";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonAddMinus){
			enteredAnswer += "-";
			answerText.setText(enteredAnswer);
		} else if (v.getId() == R.id.buttonClear){
			if (enteredAnswer.length() > 0){
				enteredAnswer = enteredAnswer.substring(0, enteredAnswer.length() - 1);
			}
			answerText.setText(enteredAnswer);
		}
	}
}
