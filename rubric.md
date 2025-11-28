# Project Assessment Rubric

**Project:** To-Do List Application
**Section:** C2A
**Course:** Object Oriented Programming

## Grading Breakdown

### 1. Class Diagram Completeness (25%)
**Score:** 1/5
**Notes:** No class diagram found in the project repository. No PNG, PDF, image files, or Figma links were provided in the documentation. This is a critical missing component for an OOP project assessment.

### 2. Java Program - OOP Concepts (50%)
**Score:** 5/5
**Notes:**
- **Encapsulation (Excellent):** All task classes properly encapsulate data with private fields (title, category, status) and public getter/setter methods. TaskManager encapsulates the task list with private List<Task> and controlled access methods.
- **Inheritance (Excellent):** Clean inheritance hierarchy with abstract Task base class and three concrete subclasses (PersonalTask, WorkTask, UrgentTask) that extend Task. Protected fields in base class properly inherited by subclasses.
- **Polymorphism (Excellent):** Strong polymorphic implementation with abstract displayTask() method in Task class, overridden in each subclass with specific behavior. Runtime polymorphism demonstrated through List<Task> collection storing different task types.
- **Abstraction (Strong):** Abstract Task class provides clear abstraction with abstract displayTask() method enforcing implementation contract. TaskManager class abstracts file operations and task management logic from the UI layer.

Outstanding OOP implementation with all four core concepts properly demonstrated. The code shows mature understanding of object-oriented design principles with JavaFX GUI integration and file persistence.

### 3. Git Usage & Collaboration (15%)
**Score:** 3/5
**Notes:** Moderate git usage with 6 total commits from 2 contributors (Michael: 5 commits, Michael Ong: 1 commit). Based on members.md, this is a group project with 3 members (Cortez, Diño, Ocampo), but git history only shows activity from one student member (likely Diño) plus external/instructor collaboration. Collaborative development not strongly evident in commit history.

### 4. Base Grade (10%)
**Score:** 5/5

---

## Final Grade: 70/100

*Assessment generated based on project analysis.*
