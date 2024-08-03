#ifndef __ALMANAC_H
#define __ALMANAC_H

#include <graal_isolate.h>


#if defined(__cplusplus)
extern "C" {
#endif

void printlnCalendar(graal_isolatethread_t*);

char* defaultCalendar(graal_isolatethread_t*, char*);

char* dayCalendar(graal_isolatethread_t*, char*);

char* monthCalendar(graal_isolatethread_t*, char*);

char* yearCalendar(graal_isolatethread_t*, char*);

int run_main(int argc, char** argv);

#if defined(__cplusplus)
}
#endif
#endif
