package com.example.android;
 
// Declare any non-default types here with import statements
 
/** Example service interface */
interface IRemoteService {
    /** Request the process ID of this service, to do evil things with it. */
    int getCount();

}