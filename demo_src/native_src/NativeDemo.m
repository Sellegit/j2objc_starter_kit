#import "NativeDemo.h"

@implementation NativeDemo

- (id)init
{
    self = [super init];
    if (self) {
    }
    
    return self;
}

- (NSString *)getText:(int) count withWho:(NSString *) who{
    return [@"Hello " stringByAppendingString:who];
}

- (int)getInt:(int (^)(void)) block{
    return block();
}

@end
