package com.example.francisco.trial;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;


/**
 * Created by Francisco on 04/06/2016.
 */
public class SoundMeter {
    private static final int sampleRate = 8000;
    private AudioRecord audio;
    private int bufferSize;
    private double lastLevel = 0;


    public void start() {
        try {
            bufferSize = AudioRecord
                    .getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO,
                            AudioFormat.ENCODING_PCM_16BIT);
        } catch (Exception e) {
            android.util.Log.e("TrackingFlow", "Exception", e);
        }

        audio = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufferSize);

        audio.startRecording();
    }

    /**
     * Functionality that gets the sound level out of the sample
     */
    public double getAmplitud() {

        try {
            short[] buffer = new short[bufferSize];

            int bufferReadResult = 1;

            if (audio != null) {

                // Sense the voice...
                bufferReadResult = audio.read(buffer, 0, bufferSize);
                double sumLevel = 0;
                for (int i = 0; i < bufferReadResult; i++) {
                    sumLevel += buffer[i];
                }
                lastLevel = Math.abs((sumLevel / bufferReadResult));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastLevel;
    }

    public void stop() {
        try {
            if (audio != null) {
                audio.stop();
                audio.release();
                audio = null;
            }
        }
        catch (Exception e) {e.printStackTrace();}
    }
}
