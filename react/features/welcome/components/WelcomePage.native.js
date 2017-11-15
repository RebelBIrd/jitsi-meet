import React from 'react';
import { TouchableHighlight, View } from 'react-native';
import { connect } from 'react-redux';

import { translate } from '../../base/i18n';
import { MEDIA_TYPE } from '../../base/media';
import { LoadingIndicator, Text } from '../../base/react';
import { ColorPalette } from '../../base/styles';
import { createDesiredLocalTracks } from '../../base/tracks';

import { AbstractWelcomePage, _mapStateToProps } from './AbstractWelcomePage';
import LocalVideoTrackUnderlay from './LocalVideoTrackUnderlay';
import { appNavigate } from '../../app';
import { WELCOME_PAGE_SHOWED } from '../actionTypes';
import styles from './styles';

/**
 * The native container rendering the welcome page.
 *
 * @extends AbstractWelcomePage
 */
class WelcomePage extends AbstractWelcomePage {
    /**
     * WelcomePage component's property types.
     *
     * @static
     */
    static propTypes = AbstractWelcomePage.propTypes;

    /**
     * Implements React's {@link Component#componentWillMount()}. Invoked
     * immediately before mounting occurs. Creates a local video track if none
     * is available.
     *
     * @inheritdoc
     * @returns {void}
     */
    componentWillMount() {
        super.componentWillMount();

        this.props.dispatch(createDesiredLocalTracks(MEDIA_TYPE.VIDEO));
    }

    /**
     * Implements React's {@link Component#componentWillMount()}. Invoked
     * immediately before mounting occurs. Creates a local video track if none
     * is available.
     *
     * @inheritdoc
     * @returns {void}
     */
    componentDidMount() {
        const { _showed, dispatch, _room } = this.props;

        if (_showed === false) {
            this.timer = setTimeout(
                () => dispatch(appNavigate(_room)), 800);
        }
    }

    /**
     * Implements React's {@link Component#componentWillUnmount()}. Invoked
     * immediately before mounting occurs. Creates a local video track if none
     * is available.
     *
     * @inheritdoc
     * @returns {void}
     */
    componentWillUnmount() {
        this.props.dispatch({ type: WELCOME_PAGE_SHOWED });
        this.timer && clearTimeout(this.timer);
    }


    /**
     * Implements React's {@link Component#render()}. Renders a prompt for
     * entering a room name.
     *
     * @inheritdoc
     * @returns {ReactElement}
     */
    render() {
        // const { t } = this.props;

        return (
            <LocalVideoTrackUnderlay style = { styles.welcomePage }>
                {}
            </LocalVideoTrackUnderlay>
        );
    }

    /**
     * Renders the join button.
     *
     * @private
     * @returns {ReactElement}
     */
    _renderJoinButton() {
        let children;

        /* eslint-disable no-extra-parens */

        if (this.state.joining) {
            // TouchableHighlight is picky about what its children can be, so
            // wrap it in a native component, i.e. View to avoid having to
            // modify non-native children.
            children = (
                <View>
                    <LoadingIndicator color = { styles.buttonText.color } />
                </View>
            );
        } else {
            children = (
                <Text style = { styles.buttonText }>
                    { this.props.t('welcomepage.join') }
                </Text>
            );
        }

        /* eslint-enable no-extra-parens */

        return (
            <TouchableHighlight
                accessibilityLabel = { 'Tap to Join.' }
                disabled = { this._isJoinDisabled() }
                onPress = { this._onJoin }
                style = { styles.button }
                underlayColor = { ColorPalette.white }>
                {
                    children
                }
            </TouchableHighlight>
        );
    }

}

export default translate(connect(_mapStateToProps)(WelcomePage));
