#include "rwlock.h"

void InitalizeReadWriteLock(struct read_write_lock * rw)
{
  //	Write the code for initializing your read-write lock.
  	rw->actiread = 0;
  	rw->actiwri = 0;
	sem_init(&rw->lock, 0, 1);
	sem_init(&rw->writelock, 0, 1);
	sem_init(&rw->readlock, 0, 1);
}

void ReaderLock(struct read_write_lock * rw)
{
  //	Write the code for aquiring read-write lock by the reader.
  	sem_wait(&rw->readlock);
  	sem_wait(&rw->lock);
  	rw->actiread++;
  	if(rw->actiread==1){
  		sem_wait(&rw->writelock);
  	}
  	sem_post(&rw->lock);
  	sem_post(&rw->readlock);
}

void ReaderUnlock(struct read_write_lock * rw)
{
  //	Write the code for releasing read-write lock by the reader.
  	sem_wait(&rw->lock);
  	rw->actiread--;
  	if((rw->actiread==0)){
  		sem_post(&rw->writelock);
  	}
  	sem_post(&rw->lock);
}

void WriterLock(struct read_write_lock * rw)
{
  //	Write the code for aquiring read-write lock by the writer.
  	sem_wait(&rw->lock);
  	rw->actiwri++;
  	if(rw->actiwri==1){
  		sem_wait(&rw->readlock);
  	}
  	sem_post(&rw->lock);
  	sem_wait(&rw->writelock);
}

void WriterUnlock(struct read_write_lock * rw)
{
  //	Write the code for releasing read-write lock by the writer.
  	sem_post(&rw->writelock);
  	sem_wait(&rw->lock);
  	rw->actiwri--;
  	if((rw->actiwri)==0){
  		sem_post(&rw->readlock);
  	}
  	sem_post(&rw->lock);
}
