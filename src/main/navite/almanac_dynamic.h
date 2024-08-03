#ifndef __ALMANAC_H
#define __ALMANAC_H

#include <graal_isolate_dynamic.h>


#if defined(__cplusplus)
extern "C" {
#endif

typedef void (*printlnCalendar_fn_t)(graal_isolatethread_t*);

typedef char* (*defaultCalendar_fn_t)(graal_isolatethread_t*, char*);

typedef char* (*dayCalendar_fn_t)(graal_isolatethread_t*, char*);

typedef char* (*monthCalendar_fn_t)(graal_isolatethread_t*, char*);

typedef char* (*yearCalendar_fn_t)(graal_isolatethread_t*, char*);

typedef int (*run_main_fn_t)(int argc, char** argv);

#if defined(__cplusplus)
}
#endif
#endif
