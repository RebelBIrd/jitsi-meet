/* @flow */

// import { NativeModules, Share } from 'react-native';
import { NativeModules } from 'react-native';

import { MiddlewareRegistry } from '../base/redux';

// import { endShareRoom } from './actions';
import { BEGIN_SHARE_ROOM } from './actionTypes';
const RNCallBack = NativeModules.RNCallBack;

/**
 * Middleware that captures room URL sharing actions and starts the sharing
 * process.
 *
 * @param {Store} store - Redux store.
 * @returns {Function}
 */
MiddlewareRegistry.register(store => next => action => {
    switch (action.type) {
    case BEGIN_SHARE_ROOM:
        _shareRoom(action.roomURL, store.dispatch);
        break;
    }

    return next(action);
});

/**
 * Open the native sheet for sharing a specific conference/room URL.
 *
 * @param {string} roomURL - The URL of the conference/room to be shared.
 * @param {Dispatch} dispatch - The Redux dispatch function.
 * @private
 * @returns {void}
 */
function _shareRoom(roomURL: string, dispatch: Function) {
    // TODO The following display/human-readable strings were submitted for
    // review before i18n was introduces in react/. However, I reviewed it
    // afterwards. Translate the display/human-readable strings.
    // const message = 
    // `Click the following link to join the meeting: ${roomURL}`;
    // const title = `${NativeModules.AppInfo.name} Conference`;
    // const onFulfilled
    //     = (shared: boolean) => dispatch(endShareRoom(roomURL, shared));

    // Share.share(
    //     /* content */ {
    //         message,
    //         title
    //     },
    //     /* options */ {
    //         dialogTitle: title, // Android
    //         subject: title // iOS
    //     })
    //     .then(
    //         /* onFulfilled */ value => {
    //             onFulfilled(value.action === Share.sharedAction);
    //         },
    //         /* onRejected */ reason => {
    //             console.error(
    //                 `Failed to share conference/room URL ${roomURL}:`,
    //                 reason);
    //             onFulfilled(false);
    //         });
    // dispatch({
    //     type: 'NOTHINGS'
    // });

    console.log(dispatch);
    RNCallBack.shareRoom(roomURL);
}
