package mcproject.instanotesv1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.test.rule.ActivityTestRule;

class CLTest<F extends Fragment> extends ActivityTestRule<Help_2015037> {

    private final Class<F> mFragmentClass;
    private F mFragment;

    public CLTest(final Class<F> fragmentClass) {
        super(Help_2015037.class, true, false);
        mFragmentClass = fragmentClass;
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();

        getActivity().runOnUiThread(() -> {
            try {
                //Instantiate and insert the fragment into the container layout
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                try {
                    mFragment = mFragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                transaction.replace(R.id.main, mFragment);
                transaction.commit();
            }  catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }
    public F getFragment(){
        return mFragment;
    }
}