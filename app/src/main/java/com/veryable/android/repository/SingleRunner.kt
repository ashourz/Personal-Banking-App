package com.veryable.android.repository

/* Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/*
* Single Runner class is a mutex to be used in conjunction with write operations to the database to
* prevent simultaneous write commands from being execute
* */
/**
 * A helper class to execute tasks sequentially in coroutines.
 *
 * Calling [afterPrevious] will always ensure that all previously requested work completes prior to
 * calling the block passed. Any future calls to [afterPrevious] while the current block is running
 * will wait for the current block to complete before starting.
 *
 * /***************************************************************************************
 *    Title: <title of program/source code>
 *    Author: Sean McQuillan
 *    Date: Sep 27,2021
 *    Availability:
 *      https://medium.com/androiddevelopers/coroutines-on-android-part-iii-real-work-2ba8a2ec2f45
 *      https://gist.github.com/objcode/7ab4e7b1df8acd88696cb0ccecad16f7
 *****************************************************************************************/
 *
 */
class SingleRunner {
    /**
     * A coroutine mutex implements a lock that may only be taken by one coroutine at a time.
     */
    private val mutex = Mutex()

    /**
     * Ensure that the block will only be executed after all previous work has completed.
     *
     * When several coroutines call afterPrevious at the same time, they will queue up in the order
     * that they call afterPrevious. Then, one coroutine will enter the block at a time.
     *
     * In the following example, only one save operation (user or song) will be executing at a time.
     *
     * ```
     * class UserAndSongSaver {
     *    val singleRunner = SingleRunner()
     *
     *    fun saveUser(user: User) {
     *        singleRunner.afterPrevious { api.post(user) }
     *    }
     *
     *    fun saveSong(song: Song) {
     *        singleRunner.afterPrevious { api.post(song) }
     *    }
     * }
     * ```
     *
     * @param block the code to run after previous work is complete.
     */
    suspend fun <T> afterPrevious(block: suspend () -> T): T {
        // Before running the block, ensure that no other blocks are running by taking a lock on the
        // mutex.

        // The mutex will be released automatically when we return.

        // If any other block were already running when we get here, it will wait for it to complete
        // before entering the `withLock` block.
        mutex.withLock {
            return block()
        }
    }
}
