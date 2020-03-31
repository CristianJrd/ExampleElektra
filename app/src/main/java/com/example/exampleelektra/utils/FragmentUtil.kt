package com.sento.fragments.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.exampleelektra.MainActivity
import com.example.exampleelektra.R

object FragmentUtil {

    //                                  CHECK
    // ========================================================================================
    fun hadFragment(activity: AppCompatActivity): Boolean {
        return activity.supportFragmentManager.backStackEntryCount != 0
    }

    fun getFragmentByTag(appCompatActivity: AppCompatActivity, tag: String): Fragment? {
        return appCompatActivity.supportFragmentManager.findFragmentByTag(tag)
    }

    // ========================================================================================
    //                                  REPLACE
    // ========================================================================================
    fun replaceFragment(activity: AppCompatActivity, contentId: Int, fragment: Fragment) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun replaceFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStackImmediate()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(contentId, fragment)
        transaction.commitAllowingStateLoss()
    }

    fun replaceFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, Tag: String) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStackImmediate()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, Tag)
        transaction.commitAllowingStateLoss()
    }

    fun removeReplaceFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, Tag: String) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStackImmediate()
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(contentId, fragment, Tag)
        transaction.commitAllowingStateLoss()
    }

    fun replaceNoBackFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, Tag: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, Tag)
        transaction.disallowAddToBackStack() // <-- This makes magic!
        transaction.commit()
    }

    fun inverseReplaceFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, Tag: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, Tag)
        transaction.commitAllowingStateLoss()
    }

    fun navReplaceFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, tag: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun replaceFragmentSharedElement(activity: FragmentActivity, contentId: Int,
                                     fragment: Fragment, tag: String,
                                     name: String, sharedElement: View) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.addSharedElement(sharedElement, name)
        transaction.replace(contentId, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, tag: String, parent: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, tag)
        transaction.addToBackStack(parent)
        transaction.commit()
    }

    fun replaceFragment(fragmentManager: FragmentManager, contentId: Int, fragment: Fragment, tag: String) {
        fragmentManager.let {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(contentId, fragment, tag)
            transaction.addToBackStack(tag)
            transaction.commit()
        }
    }

    fun replaceFragment(context: Context, contentId: Int, fragment: Fragment, tag: String) {
        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, tag)
        transaction.commit()
    }

    fun replaceFragmentNoBack(fragmentManager: FragmentManager, contentId: Int, fragment: Fragment, tag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(contentId, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // ========================================================================================
    //                                  ADD
    // ========================================================================================

    fun addFragment(activity: AppCompatActivity, contentId: Int, fragment: Fragment, tag: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.add(contentId, fragment, tag)
        transaction.addToBackStack(Fragment::class.java.name)
        transaction.commit()
    }

    fun addFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, tag: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.add(contentId, fragment, tag)
        transaction.addToBackStack(Fragment::class.java.name)
        transaction.commit()
    }

    fun addFragment(activity: FragmentActivity, contentId: Int, fragment: Fragment, tag: String, parent: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.add(contentId, fragment, tag)
        transaction.addToBackStack(parent)
        transaction.commit()
    }

    fun addFragment(context: Context, contentId: Int, fragment: Fragment, tag: String) {
        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
        transaction.add(contentId, fragment, tag)
        // transaction.addToBackStack(tag)
        transaction.commit()
    }
    // ========================================================================================
    //                                  REMOVE
    // ========================================================================================
    fun removeFragment(activity: AppCompatActivity, fragment: Fragment) {
        val fm = activity.supportFragmentManager
        fm.beginTransaction()
                .remove(fragment)
                .commit()
        fm.popBackStack()
    }

    fun removeFragment(activity: FragmentActivity, fragment: Fragment) {
        val fm = activity.supportFragmentManager
        fm.beginTransaction()
                .remove(fragment)
                .commit()
        fm.popBackStack()
    }

    fun removeAllFragment(activity: AppCompatActivity) {
        val fm = activity.supportFragmentManager
        for (fragment in fm.fragments) {
            if (fragment != null) {
                if (fm.fragments.size > 1) {
                    fm.beginTransaction().remove(fragment).commit()
                    fm.popBackStack()
                }
            }
        }
    }

    fun removeAllFragment(activity: FragmentActivity) {
        val fm = activity.supportFragmentManager
        for (fragment in fm.fragments) {
            if (fragment != null) {
                if (fm.fragments.size > 1) {
                    fm.beginTransaction().remove(fragment).commit()
                    fm.popBackStack()
                }
            }
        }
    }

    fun removeFragment(activity: AppCompatActivity) {
        val fm = activity.supportFragmentManager
        if (fm != null) {
            val size = fm.fragments.size
            if (size >= 1) {
                val fragment = fm.fragments[size - 1]
                if (fragment != null) {
                    fm.beginTransaction().remove(fragment).commit()
                    fm.popBackStack()
                }
            }
        }
    }

    fun removeFragment(activity: FragmentActivity) {
        val fm = activity.supportFragmentManager
        if (fm != null && fm.fragments.size >= 1)
            fm.popBackStack()
    }

    // ========================================================================================
    //                                  SHOW
    // ========================================================================================
    fun showFragment(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .show(fragment)
                .commit()
    }

    fun showFragment(activity: FragmentActivity, containerId: Int, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit()
    }

    // ========================================================================================
    //                                  HIDE
    // ========================================================================================
    fun hideFragment(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .hide(fragment)
                .commit()
    }


    // ========================================================================================
    //                                  ATTACH AND DETACH
    // ========================================================================================
    fun attachFragment(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .attach(fragment)
                .commit()
    }

    fun detachFragment(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .detach(fragment)
                .commit()
    }
}