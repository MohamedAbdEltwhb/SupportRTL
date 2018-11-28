package com.example.mm.supportrtl;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mm.supportrtl.databinding.ActivityMainBinding;

import com.example.mm.supportrtl.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding mBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BoardingPassInfo fakeBoardingInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(fakeBoardingInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo fakeBoardingInfo) {

        mBinding.textViewPassengerName.setText(fakeBoardingInfo.passengerName);
        // COMPLETED (7) Use the flightInfor attribute in mBinding below to get the appropiate text Views
        mBinding.flightInfo.textViewOriginAirport.setText(fakeBoardingInfo.originCode);
        mBinding.flightInfo.textViewFlightCode.setText(fakeBoardingInfo.flightCode);
        mBinding.flightInfo.textViewDestinationAirport.setText(fakeBoardingInfo.destCode);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String boardingTime = formatter.format(fakeBoardingInfo.boardingTime);
        String departureTime = formatter.format(fakeBoardingInfo.departureTime);
        String arrivalTime = formatter.format(fakeBoardingInfo.arrivalTime);

        mBinding.textViewBoardingTime.setText(boardingTime);
        mBinding.textViewDepartureTime.setText(departureTime);
        mBinding.textViewArrivalTime.setText(arrivalTime);

        long totalMinutesUntilBoarding = fakeBoardingInfo.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        mBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        // COMPLETED (8) Use the boardingInfo attribute in mBinding below to get the appropiate text Views
        mBinding.boardingInfo.textViewTerminal.setText(fakeBoardingInfo.departureTerminal);
        mBinding.boardingInfo.textViewGate.setText(fakeBoardingInfo.departureGate);
        mBinding.boardingInfo.textViewSeat.setText(fakeBoardingInfo.seatNumber);

    }
}
