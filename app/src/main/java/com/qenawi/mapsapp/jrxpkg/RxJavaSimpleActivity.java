package com.qenawi.mapsapp.jrxpkg;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qenawi.mapsapp.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
    observables representing sources of data

    subscribers (or observers) listening to the observables

    a set of methods for modifying and composing the data

An observable emits items; a subscriber consumes those item
 */
public class RxJavaSimpleActivity extends AppCompatActivity {
     CompositeDisposable disposable = new CompositeDisposable();
     /*
     CompositeDisposable is just a class to keep all your disposables in the same place to you can dispose all of then at once. Like:
      */
    public int value =0;
    final Observable<Integer> serverDownloadObservable = Observable.create(emitter ->
    {
        SystemClock.sleep(10000); // simulate delay
        emitter.onNext(5);
        emitter.onComplete();
    });
    /*
    Observable<T>
Emits 0 or n items and terminates with an success or an error event.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_simple);
        Button f=findViewById(R.id.button);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
view.setEnabled(false);
                Disposable subscribe=serverDownloadObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                        subscribe(integer ->
                                {
updateTheUserInterface(integer);
view.setEnabled(true);
                                });

disposable.add(subscribe);
            }
        });
    }
    private void updateTheUserInterface(int integer) {
        TextView view = (TextView) findViewById(R.id.resultView);
        view.setText(String.valueOf(integer));
    }
    public void onClick(View view) {
        Toast.makeText(this, "Still active " + value++, Toast.LENGTH_SHORT).show();
    }
}
