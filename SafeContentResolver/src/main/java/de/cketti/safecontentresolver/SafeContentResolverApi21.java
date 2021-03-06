/*
 * Copyright (C) 2016 cketti
 * Copyright (C) 2016 Dominik Schürmann <dominik@dominikschuermann.de>
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
package de.cketti.safecontentresolver;


import java.io.FileDescriptor;
import java.io.FileNotFoundException;

import android.content.Context;
import android.support.annotation.NonNull;
import android.system.Os;
import android.system.StructStat;


final class SafeContentResolverApi21 extends SafeContentResolver {
    SafeContentResolverApi21(Context context) {
        super(context);
    }

    @Override
    protected int getFileUidOrThrow(@NonNull FileDescriptor fileDescriptor) throws FileNotFoundException {
        try {
            StructStat st = Os.fstat(fileDescriptor);
            return st.st_uid;
        } catch (android.system.ErrnoException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
