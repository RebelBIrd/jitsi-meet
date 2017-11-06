//
//  JitsiMeetCallRN.h
//  JitsiMeet
//
//  Created by 周季伟 on 2017/10/26.
//  Copyright © 2017年 Jitsi. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTEventEmitter.h>

@interface JitsiMeetCallRN : RCTEventEmitter<RCTBridgeModule>

+ (void)sendMessageToHandUpMeeting;

@end
