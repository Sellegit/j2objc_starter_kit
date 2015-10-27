#import <Foundation/Foundation.h>

@interface NativeDemo : NSObject

- (NSString *)getText:(int) count withWho:(NSString *) who;

- (int)getInt:(int (^)(void)) block;

@end
