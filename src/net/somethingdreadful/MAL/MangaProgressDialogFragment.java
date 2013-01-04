package net.somethingdreadful.MAL;

import net.simonvt.widget.NumberPicker;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MangaProgressDialogFragment extends DialogFragment {

    View view;

    NumberPicker chapterPicker;
    NumberPicker volumePicker;

    int chaptersTotal;
    int chaptersRead;
    int chapterPickerValue;

    int volumesTotal;
    int volumesRead;
    int volumePickerValue;

    public MangaProgressDialogFragment()
    {

    }

    public interface MangaDialogDismissedListener
    {
        void onMangaDialogDismissed(int newChapterValue, int newVolumeValue);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v("MALX", "onCreateDialog Fired");

        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_manga_progress, null);

        return new AlertDialog.Builder(getActivity())
        .setPositiveButton("Update", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                ((DetailView) getActivity()).onMangaDialogDismissed(chapterPicker.getValue(), volumePicker.getValue());
                dismiss();
            }
        }
                ).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        dismiss();
                    }
                }
                        ).setView(view).setTitle("I've read:").create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state)
    {
        //        View view = inflater.inflate(R.layout.dialog_manga_progress, container);

        if (state == null)
        {
            //			totalEpisodes = Integer.parseInt(((DetailView) getActivity()).mAr.getTotal());
            //			watchedEpisodes = ((DetailView) getActivity()).mAr.getPersonalProgress();
            //			pickerValue = watchedEpisodes;

            chaptersTotal = Integer.parseInt(((DetailView) getActivity()).mMr.getTotal());
            chaptersRead = ((DetailView) getActivity()).mMr.getPersonalProgress();
            chapterPickerValue = chaptersRead;

            volumesTotal = Integer.parseInt(((DetailView) getActivity()).mMr.getVolumeTotal());
            volumesRead = ((DetailView) getActivity()).mMr.getVolumeProgress();
            volumePickerValue = volumesRead;

        }
        else
        {
            //			totalEpisodes = state.getInt("totalEpisodes");
            //			watchedEpisodes = state.getInt("watchedEpisodes");
            //			pickerValue = state.getInt("pickerValue");

            chaptersTotal = state.getInt("chaptersTotal");
            chaptersRead = state.getInt("chaptersRead");
            chapterPickerValue = state.getInt("chapterPickerValue");

            volumesTotal = state.getInt("volumesTotal");
            volumesRead = state.getInt("volumesRead");
            volumePickerValue = state.getInt("volumePickerValue");
        }


        chapterPicker = (NumberPicker) view.findViewById(R.id.chapterPicker);
        volumePicker = (NumberPicker) view.findViewById(R.id.volumePicker);

        //        getDialog().setTitle("I've read:");

        chapterPicker.setMinValue(0);
        volumePicker.setMinValue(0);

        if (chaptersTotal != 0)
        {
            chapterPicker.setMaxValue(chaptersTotal);
        }
        else
        {
            chapterPicker.setMaxValue(999);
        }

        if (volumesTotal != 0)
        {
            volumePicker.setMaxValue(volumesTotal);
        }
        else
        {
            volumePicker.setMaxValue(999);
        }

        chapterPicker.setWrapSelectorWheel(false);
        volumePicker.setWrapSelectorWheel(false);

        chapterPicker.setValue(chapterPickerValue);
        volumePicker.setValue(volumePickerValue);

        return null;
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {



    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        this.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {

        state.putInt("chaptersTotal", chaptersTotal);
        state.putInt("chaptersRead", chaptersRead);
        state.putInt("chapterPickerValue", chapterPicker.getValue());

        state.putInt("volumesTotal", volumesTotal);
        state.putInt("volumesRead", volumesRead);
        state.putInt("volumePickerValue", volumePicker.getValue());

        super.onSaveInstanceState(state);
    }



}