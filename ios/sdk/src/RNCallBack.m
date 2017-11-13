//
//  RNCallBack.m
//  JitsiMeet
//
//  Created by 周季伟 on 2017/11/13.
//  Copyright © 2017年 Jitsi. All rights reserved.
//

#import "RNCallBack.h"

@implementation RNCallBack

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(shareRoom:(NSString *)name) {
    NSLog(@"dahuang-----%@",name);
}

@end
