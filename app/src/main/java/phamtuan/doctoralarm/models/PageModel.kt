/*
 * Copyright (C) 2013 Onur-Hayri Bakici
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package phamtuan.doctoralarm.models

import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

class PageModel<T>(val parentView: ViewGroup, var indicator: T?) {

     val mChildren: MutableList<View>?

    init {
        val size = parentView.childCount
        mChildren = ArrayList<View>(size)

        for (i in 0..size - 1) {
            mChildren.add(parentView.getChildAt(i))
        }
    }

    /**

     * @return `true` if the model has child views.
     */
    fun hasChildren(): Boolean {
        return mChildren != null && mChildren.size != 0
    }


    private fun emptyChildren() {
        if (hasChildren()) {
            mChildren!!.clear()
        }
    }

    val children: List<View>?
        get() = mChildren

    fun removeAllChildren() {
        parentView.removeAllViews()
        emptyChildren()
    }

    fun addChild(child: View) {
        addViewToParent(child)
        mChildren!!.add(child)
    }

    fun removeViewFromParent(view: View) {
        parentView.removeView(view)
    }

    fun addViewToParent(view: View) {
        parentView.addView(view)
    }
}
