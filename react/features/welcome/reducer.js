// @flow

import { ReducerRegistry } from '../base/redux';
import { WELCOME_PAGE_SHOWED } from './actionTypes';


ReducerRegistry.register(
    'features/welcome',
    (state = { showed: false }, action) => {
        switch (action.type) {

        case WELCOME_PAGE_SHOWED:
            return {
                ...state,
                showed: true
            };
        }

        return state;
    });
