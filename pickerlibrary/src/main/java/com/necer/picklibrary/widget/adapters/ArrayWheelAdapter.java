/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.necer.picklibrary.widget.adapters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * The simple Array wheel adapter
 * @param <T> the element type
 */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    
    // items
    private List<T> list;

    /**
     * Constructor
     * @param context the current context
     * @param items the items
     */
    public ArrayWheelAdapter(Context context, T items[]) {
        super(context);
        list = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            list.add(items[i]);
        }
        
    }

    public ArrayWheelAdapter(Context context, List<T> list) {
        super(context);
        this.list = list;

    }

    
    @Override
    public CharSequence getItemText(int index) {
        if (index >= 0 && index < list.size()) {
            T item = list.get(index);
            if (item instanceof CharSequence) {
                return (CharSequence) item;
            }
            return item.toString();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        //return items.length;

        return list.size();
    }
}
