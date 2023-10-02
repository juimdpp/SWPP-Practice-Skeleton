package com.example.lab4application;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

public class DiceRollViewModelTest {
    private final RollDiceUseCase rollDiceUseCase = mock(RollDiceUseCase.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DiceRollViewModel viewModel;

    AutoCloseable closeable;
    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        viewModel = new DiceRollViewModel();
    }
    @After
    public void teardown() throws Exception {
        closeable.close();
    }
    @Test
    public void setNumRoll_updatesUiState() {
        // GIVEN a fresh use case
        int numRoll = 5;
        String primeNumbers = "[2, 3, 5]";
        when(rollDiceUseCase.getNumRoll()).thenReturn(numRoll);
        when(rollDiceUseCase.getPrimeNumbers()).thenReturn(primeNumbers);

        // WHEN a use case is done
        // Create observer - doesn't have to do anything!
        Observer<DiceRollUiState> observer = new Observer<DiceRollUiState>() {
            @Override
            public void onChanged(DiceRollUiState diceRollUiState) {
            }
        };

        try {
            // Observe the LiveData
            viewModel.getUiState().observeForever(observer);
            // Call the function
            viewModel.setNumRoll(rollDiceUseCase);

            // THEN UiState is changed
            DiceRollUiState result = viewModel.getUiState().getValue();
            assertNotNull(result);
            assertEquals(-1, result.getFirstDieValue());
            assertEquals(-1, result.getSecondDieValue());
            assertEquals(numRoll, result.getNumRolls());
            assertEquals(primeNumbers, result.getPrimeNumbers());

        } finally {
            // Don't forget to remove the observer!
            viewModel.getUiState().removeObserver(observer);
        }
    }

    @Test
    public void getData_checkUiStateChange() {
        // GIVEN a fresh use case
        int firstNumber = 2;
        int secondNumber = 4;
        int numRoll = 5;
        String primeNumbers = "[2, 3, 5, 7]";
        when(rollDiceUseCase.getFirstDieValue()).thenReturn(firstNumber);
        when(rollDiceUseCase.getSecondDieValue()).thenReturn(secondNumber);
        when(rollDiceUseCase.getNumRoll()).thenReturn(numRoll);
        when(rollDiceUseCase.getPrimeNumbers()).thenReturn(primeNumbers);

        // WHEN a use case is done
        // Create observer - doesn't have to do anything!
        Observer<DiceRollUiState> observer = new Observer<DiceRollUiState>() {
            @Override
            public void onChanged(DiceRollUiState diceRollUiState) {
            }
        };

        try {
            // Observe the LiveData
            viewModel.getUiState().observeForever(observer);
            // Call the function
            viewModel.getData(rollDiceUseCase);

            // THEN UiState is changed
            DiceRollUiState result = viewModel.getUiState().getValue();
            assertNotNull(result);
            assertEquals(firstNumber, result.getFirstDieValue());
            assertEquals(secondNumber, result.getSecondDieValue());
            assertEquals(numRoll, result.getNumRolls());
            assertEquals(primeNumbers, result.getPrimeNumbers());

        } finally {
            // Don't forget to remove the observer!
            viewModel.getUiState().removeObserver(observer);
        }
    }

}