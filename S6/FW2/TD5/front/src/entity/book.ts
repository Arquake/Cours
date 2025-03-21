import { AuthorSummary } from "./AuthorSummary";

export interface Book {
    id: number,
    title: string,
    publisher: string,
    year: number,
    backcover: string,
    isbn: string,
    author: AuthorSummary[]
}
