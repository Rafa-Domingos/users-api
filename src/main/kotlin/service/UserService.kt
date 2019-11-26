package service

import arrow.core.fix
import configuration.server.RepositoryContext
import exceptions.EntityNotFoundException
import model.User
import model.UserRole

fun saveTeacher(user: User) =
    repository.save(user.copy(role = UserRole.TEACHER)).run(RepositoryContext).fix().extract()

fun saveStudent(user: User) =
    repository.save(user.copy(role = UserRole.STUDENT)).run(RepositoryContext).fix().extract()

fun getTeacherById(id: String) =
    repository.getByIdAndRole(id, UserRole.TEACHER).run(RepositoryContext).fix().extract() ?: throw EntityNotFoundException()

fun getStudentById(id: String) =
    repository.getByIdAndRole(id, UserRole.STUDENT).run(RepositoryContext).fix().extract() ?: throw EntityNotFoundException()

fun delete(id: String) = repository.delete(id).run(RepositoryContext)

fun replace(id: String, user: User) = repository.replace(id, user).run(RepositoryContext).fix().extract()
