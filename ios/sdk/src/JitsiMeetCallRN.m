//
//  JitsiMeetCallRN.m
//  JitsiMeet
//
//  Created by 周季伟 on 2017/10/26.
//  Copyright © 2017年 Jitsi. All rights reserved.
//

#import "JitsiMeetCallRN.h"

@implementation JitsiMeetCallRN


RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents {
    return @[@"handleMeetingUp"];
}

- (void)startObserving {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(handleMeetingUp)
                                                 name:@"handleMeetingUp"
                                               object:nil];
}

- (void)stopObserving {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)handleMeetingUp {
    [self sendEventWithName:@"handleMeetingUp" body:@{}];
}

+ (void)sendMessageToHandUpMeeting {
    [[NSNotificationCenter defaultCenter] postNotificationName:@"handleMeetingUp"
                                                        object:self
                                                      userInfo:@{}];
}

@end
